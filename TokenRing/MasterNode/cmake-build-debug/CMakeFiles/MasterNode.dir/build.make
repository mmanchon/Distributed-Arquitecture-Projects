# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2018.2.1\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2018.2.1\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/MasterNode.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/MasterNode.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/MasterNode.dir/flags.make

CMakeFiles/MasterNode.dir/main.c.obj: CMakeFiles/MasterNode.dir/flags.make
CMakeFiles/MasterNode.dir/main.c.obj: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/MasterNode.dir/main.c.obj"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\MasterNode.dir\main.c.obj   -c C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\main.c

CMakeFiles/MasterNode.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/MasterNode.dir/main.c.i"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\main.c > CMakeFiles\MasterNode.dir\main.c.i

CMakeFiles/MasterNode.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/MasterNode.dir/main.c.s"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\main.c -o CMakeFiles\MasterNode.dir\main.c.s

CMakeFiles/MasterNode.dir/Utils.c.obj: CMakeFiles/MasterNode.dir/flags.make
CMakeFiles/MasterNode.dir/Utils.c.obj: ../Utils.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/MasterNode.dir/Utils.c.obj"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\MasterNode.dir\Utils.c.obj   -c C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\Utils.c

CMakeFiles/MasterNode.dir/Utils.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/MasterNode.dir/Utils.c.i"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\Utils.c > CMakeFiles\MasterNode.dir\Utils.c.i

CMakeFiles/MasterNode.dir/Utils.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/MasterNode.dir/Utils.c.s"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\Utils.c -o CMakeFiles\MasterNode.dir\Utils.c.s

CMakeFiles/MasterNode.dir/Network.c.obj: CMakeFiles/MasterNode.dir/flags.make
CMakeFiles/MasterNode.dir/Network.c.obj: ../Network.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object CMakeFiles/MasterNode.dir/Network.c.obj"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\MasterNode.dir\Network.c.obj   -c C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\Network.c

CMakeFiles/MasterNode.dir/Network.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/MasterNode.dir/Network.c.i"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\Network.c > CMakeFiles\MasterNode.dir\Network.c.i

CMakeFiles/MasterNode.dir/Network.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/MasterNode.dir/Network.c.s"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\Network.c -o CMakeFiles\MasterNode.dir\Network.c.s

# Object files for target MasterNode
MasterNode_OBJECTS = \
"CMakeFiles/MasterNode.dir/main.c.obj" \
"CMakeFiles/MasterNode.dir/Utils.c.obj" \
"CMakeFiles/MasterNode.dir/Network.c.obj"

# External object files for target MasterNode
MasterNode_EXTERNAL_OBJECTS =

MasterNode.exe: CMakeFiles/MasterNode.dir/main.c.obj
MasterNode.exe: CMakeFiles/MasterNode.dir/Utils.c.obj
MasterNode.exe: CMakeFiles/MasterNode.dir/Network.c.obj
MasterNode.exe: CMakeFiles/MasterNode.dir/build.make
MasterNode.exe: CMakeFiles/MasterNode.dir/linklibs.rsp
MasterNode.exe: CMakeFiles/MasterNode.dir/objects1.rsp
MasterNode.exe: CMakeFiles/MasterNode.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Linking C executable MasterNode.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\MasterNode.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/MasterNode.dir/build: MasterNode.exe

.PHONY : CMakeFiles/MasterNode.dir/build

CMakeFiles/MasterNode.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\MasterNode.dir\cmake_clean.cmake
.PHONY : CMakeFiles/MasterNode.dir/clean

CMakeFiles/MasterNode.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug C:\Users\Manel\CLionProjects\Distribuida\EX0\MasterNode\cmake-build-debug\CMakeFiles\MasterNode.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/MasterNode.dir/depend

