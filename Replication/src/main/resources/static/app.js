var stompClient = null;
var array = ["A1","A2","A3","B1","B2","C1","C2"];
var id = ["1","2","3","4","5","6","7","8","9"];
var json;
var server;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/greetings', function (greeting) {

            server = greeting.body;
            console.log(server);

            JSON.parse(server)["data"].forEach(function(element) {
                while (document.getElementById(element["content"]).firstChild) {
                    document.getElementById(element["content"]).removeChild(document.getElementById(element["content"]).firstChild);
                }
                var hilera = document.createElement("tr");


                var keys = Object.keys(element["value"]);
                keys.forEach(function(key){
                    var celda = document.createElement("td");
                    var textoCelda = document.createTextNode("KEY:"+key);
                    celda.appendChild(textoCelda);
                    hilera.appendChild(celda);
                });

                document.getElementById(element["content"]).appendChild(hilera);
                var hilera2 = document.createElement("tr");

                for(var i in element["value"]) {
                    var celda2 = document.createElement("td");
                    var textoCelda2 = document.createTextNode("VALUE:"+element["value"][i]);
                    celda2.appendChild(textoCelda2);
                    hilera2.appendChild(celda2);

                }
                document.getElementById(element["content"]).appendChild(hilera2);

            });


        });
        sendName();
    });
}

function sendName() {
    stompClient.send("/app/hello", {}, {});
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();

   /* $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });*/
});

