# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "XCSoar glide computer"
HOMEPAGE = ""
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=c79ff39f19dfec6d293b95dea7b07891"
SECTION = "base/app"
DEPENDS = "	libsdl \
		jpeg \
		freetype \
		libpng \
		libinput \
		boost \
		ttf-dejavu \
		sunxi-mali \
		curlpp \
"

RDEPENDS_${PN} = "	sunxi-mali \
			libinput \
			libsdl \
			udev \
"

S = "${WORKDIR}/git"
PR = "r2"

SRC_URI = 	"git://git-ro.openvario.org/xcsoar.git;protocol=http;tag=6.8_ov3 \
				 file://0001-Adapted-Flags-for-compiler-and-linker-for-cross-comp.patch \
				 file://0001-Adapted-toolchain-prefixes-for-cross-compile.patch \
				 file://0001-Disable-warnings-as-errors.patch \
				 file://0001-Override-detection-of-target-hardware.patch \
"

addtask do_package_write_ipk after do_package after do_install

do_compile() {
	echo $CC
	$CC --version
	echo "Making .."
	echo '${WORKDIR}'
	cd ${WORKDIR}/git
	make -j8 DEBUG=n DEBUG_GLIBCXX=n
}

do_install() {
	echo "Installing ..."
	install -d ${D}/opt/XCSoar/bin
	install -m 0755 ${S}/output/UNIX/bin/* ${D}/opt/XCSoar/bin

}

FILES_${PN} = " \
	/opt/XCSoar/bin/* \
"

