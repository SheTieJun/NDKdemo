
cmake_minimum_required(VERSION 3.4.1)

#C 的编译选项是 CMAKE_C_FLAGS
# 指定编译参数，可选
SET(CMAKE_CXX_FLAGS "-Wno-error=format-security -Wno-error=pointer-sign")

#设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

##当前../jni目录的所有.c .cpp源文件
#AUX_SOURCE_DIRECTORY(../jni SRC_LIST)

SET(LAME_LIBMP3_DIR  ../jni/libmp3lame_3.100)

SET(SRC_LIST
        ${LAME_LIBMP3_DIR}/bitstream.c
        ${LAME_LIBMP3_DIR}/fft.c
        ${LAME_LIBMP3_DIR}/id3tag.c
        ${LAME_LIBMP3_DIR}/mpglib_interface.c
        ${LAME_LIBMP3_DIR}/presets.c
        ${LAME_LIBMP3_DIR}/quantize.c
        ${LAME_LIBMP3_DIR}/reservoir.c
        ${LAME_LIBMP3_DIR}/tables.c
        ${LAME_LIBMP3_DIR}/util.c
        ${LAME_LIBMP3_DIR}/VbrTag.c
        ${LAME_LIBMP3_DIR}/encoder.c
        ${LAME_LIBMP3_DIR}/gain_analysis.c
        ${LAME_LIBMP3_DIR}/lame.c
        ${LAME_LIBMP3_DIR}/newmdct.c
        ${LAME_LIBMP3_DIR}/psymodel.c
        ${LAME_LIBMP3_DIR}/quantize_pvt.c
        ${LAME_LIBMP3_DIR}/set_get.c
        ${LAME_LIBMP3_DIR}/takehiro.c
        ${LAME_LIBMP3_DIR}/vbrquantize.c
        ${LAME_LIBMP3_DIR}/version.c
        ../jni/lame_util.c
        )

add_library( # Sets the name of the library.
             mp3lame

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
            ${SRC_LIST})



find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )



target_link_libraries( # Specifies the target library.
                       mp3lame

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
