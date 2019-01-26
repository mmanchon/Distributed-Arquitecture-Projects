cmake_minimum_required(VERSION 3.12)
project(MasterNode C)

set(CMAKE_C_STANDARD 99)

add_executable(MasterNode main.c Utils.c Utils.h Network.c Network.h)
