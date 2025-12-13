SUMMARY = "AESD Assignment 6 - aesdsocket server"
DESCRIPTION = "Builds aesdsocket from the assignment repo and installs start script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit update-rc.d

# Use HTTPS (works without SSH keys). Replace repo and branch as needed.
SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-Vimal101091.git;protocol=https;branch=master \
           file://aesdsocket-start.sh"

PV = "1.0"
# Set to the actual commit you want to build (recommended)
SRCREV = "2886d0e5519287c702f13a65e5692e6fed1719cc"

# Build from server/ in that repo
S = "${WORKDIR}/git/server"

# Link flags (use only if needed)
TARGET_LDFLAGS += "-pthread"

# Runtime deps (if any extra libs required, add them here)
RDEPENDS_${PN} += "libgcc pthread"

# Initscript registration (update-rc.d)
INITSCRIPT_NAME = "aesdsocket"
INITSCRIPT_PARAMS = "defaults"

FILES_${PN} += "${bindir}/aesdsocket ${sysconfdir}/init.d/aesdsocket"

do_configure () {
    :
}

do_compile () {
    oe_runmake
}

do_install () {
    # Install binary
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket

    # Install init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdsocket-start.sh ${D}${sysconfdir}/init.d/aesdsocket
}


