DESCRIPTION = "The Buildkite Agent is an open-source toolkit written in Golang for securely running build jobs on any device or network"
HOMEPAGE = "https://buildkite.com/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/github.com/buildkite/agent/LICENSE.txt;md5=144ba5eac4d4147eb1d7c5f85cb1f67f"

GO_IMPORT = "github.com/buildkite/agent"
SRC_URI = "git://${GO_IMPORT}.git;protocol=https \
           file://buildkite-agent.cfg \
           file://buildkite-agent.service"
SRCREV = "eecaae408f019e5a7c2724d607ba9b3c04a95bd9"
UPSTREAM_CHECK_COMMITS = "1"

S = "${WORKDIR}/git"

inherit go
inherit goarch

SYSTEMD_SERVICE_${PN} = "buildkite-agent.service"

do_compile() {
    export GOARCH=${TARGET_GOARCH}
    export CGO_ENABLED="1"
    export CFLAGS=""
    export LDFLAGS=""
    export CGO_CFLAGS="${BUILDSDK_CFLAGS} --sysroot=${STAGING_DIR_TARGET}"
    export CGO_LDFLAGS="${BUILDSDK_LDFLAGS} --sysroot=${STAGING_DIR_TARGET}"
    cd ${S}/src/${GO_IMPORT}
    go build -o buildkite-agent
}

do_install() {
    install -d ${D}${sysconfdir}/buildkite
    install -m 0644 ${WORKDIR}/buildkite-agent.cfg ${D}${sysconfdir}/buildkite/buildkite-agent.cfg

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/buildkite-agent.service ${D}${systemd_system_unitdir}

    install -d "${D}/${bindir}"
    install -m 0755 "${S}/src/${GO_IMPORT}/buildkite-agent" "${D}${bindir}/buildkite-agent"
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
RDEPENDS_${PN}-staticdev += "bash"
RDEPENDS_${PN}-dev += "bash"

FILES_${PN} += "${systemd_system_unitdir}/buildkite-agent.service"
