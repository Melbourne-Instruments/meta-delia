SUMMARY = "Adds a custom user named "delia" with right groups membership \
	 and privileges for DELIA OS devices"
SECTION = "misc"

HOMEPAGE = "https://github.com/Melbourne-Instruments/meta-delia.git"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "\
    file://README \
    file://logo.sh \
    file://display-elk-logo.sh \
"

inherit useradd extrausers

### password generated with the command: openssl passwd "******"
DELIA_PASSWD = "RxEA3Y8sRxpxw"
USERADD_PACKAGES = "${PN}"
HOME_DIR = "/home/delia"
USERADD_PARAM_${PN} = "-g xenomai -G audio,sudo -p '${DELIA_PASSWD}' -m -d ${HOME_DIR}  -s /bin/bash delia"
GROUPADD_PARAM_${PN} = "delia; -g 2004 xenomai"
EXTRA_USERS_PARAMS = "usermod -s /bin/bash root;"

do_install() {
    install -d ${D}${HOME_DIR}
    install -m 0755 ${WORKDIR}/README ${D}${HOME_DIR}/README
    chown -R delia:delia ${D}${HOME_DIR}/
    chown delia:delia ${D}${HOME_DIR}/README

    # install elk logo display scrip
    install -d ${D}${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/logo.sh ${D}${sysconfdir}/profile.d/logo.sh
    install -m 0755 ${WORKDIR}/display-elk-logo.sh ${D}${sysconfdir}/display-elk-logo.sh
}

FILES_${PN} = "${HOME_DIR}/*"
FILES_${PN} += "${sysconfdir}/*"

RDEPENDS_${PN} += "bash"
