SUMMARY = "A user space library for accessing the rtdm audio driver and device"
HOMEPAGE = "https://github.com/Melbourne-Instruments/raspa"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=e49f4652534af377a713df3d9dec60cb"
DEPENDS = "xenomai-lib"

#Note: Same as SRCREV; Overiden from meta-<product>
PV = ""

SRC_URI = "git://github.com/Melbourne-Instruments/raspa.git"

# NOTE: This is most likely overiden as desired from meta-<product> layer with a .bbappend recipe"
SRCREV = ""

S = "${WORKDIR}/git"

inherit cmake

# Set CMAKE optimization flags
OECMAKE_C_FLAGS_RELEASE += "-O3"
OECMAKE_CXX_FLAGS_RELEASE += "-O3"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg ${PN}-staticdev"

RDEPENDS_${PN}-staticdev = ""
RDEPENDS_${PN}-dev = ""
RDEPENDS_${PN}-dbg = ""