SUMMARY = "DELIA VST (VST3) for DELIA OS."
HOMEPAGE = "https://github.com/Melbourne-Instruments/delia_vst"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

DEPENDS = "\
    gsl \
    libsndfile1 \
    fftw \
"

# Note: Same as SRCREV; Overide in meta-<product>
PV = ""
SRC_URI = "gitsm://github.com/Melbourne-Instruments/delia_vst.git"

# NOTE: Override this in the meta-<product> layer with a
# .bbappend recipe choosing the specific commit required
SRCREV = ""

S = "${WORKDIR}/git"

inherit cmake python3native

export CROSS_COMPILE="${TARGET_PREFIX}"
OECMAKE_C_FLAGS_RELEASE += "-O3"
OECMAKE_CXX_FLAGS_RELEASE += "-O3"

ROOT_HOME_DIR = "/home/root"

DEPENDS = "\
    xenomai-lib \
"

do_compile() {
    cmake \
    ${OECMAKE_GENERATOR_ARGS} \
    $oecmake_sitefile \
    ${OECMAKE_SOURCEPATH}

    cmake_runcmake_build --target ${OECMAKE_TARGET_COMPILE}
}

do_install() {
    install -d ${D}${ROOT_HOME_DIR}
    install -d ${D}${ROOT_HOME_DIR}/delia
    install -d ${D}${ROOT_HOME_DIR}/delia/delia_vst.vst3/Contents/${TARGET_ARCH}-linux
    cp "${WORKDIR}/build/VST3/Debug/delia_vst.vst3/Contents/aarch64-linux/delia_vst.so" "${D}${ROOT_HOME_DIR}/delia/delia_vst.vst3/Contents/${TARGET_ARCH}-linux/"
    cp ${WORKDIR}/build/VST3/Debug/delia_vst.vst3/Contents/*.py ${D}${ROOT_HOME_DIR}/delia/delia_vst.vst3/Contents/
    cp "${WORKDIR}/build/VST3/Debug/delia_vst.vst3/Contents/version.txt" "${D}${ROOT_HOME_DIR}/delia/delia_vst.vst3/Contents/"
}

FILES_${PN} += "${ROOT_HOME_DIR}/delia/delia_vst.vst3/*"

RDEPENDS_{PN} = "\
    xenomai-lib \
"

INSANE_SKIP_${PN} += "ldflags"
