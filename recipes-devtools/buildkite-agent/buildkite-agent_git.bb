DESCRIPTION = "The Buildkite Agent is an open-source toolkit written in Golang for securely running build jobs on any device or network"
HOMEPAGE = "https://buildkite.com/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/src/${GO_IMPORT}/LICENSE.txt;md5=144ba5eac4d4147eb1d7c5f85cb1f67f"

SRC_URI = "git://github.com/buildkite/agent.git;protocol=https"
SRCREV = "eecaae408f019e5a7c2724d607ba9b3c04a95bd9"
UPSTREAM_CHECK_COMMITS = "1"

DEPENDS += "go-dep-native"

GO_IMPORT = "agent"

inherit go
inherit goarch

do_compile() {
    export GOARCH=${TARGET_GOARCH}
    export CGO_ENABLED="1"
    export CGO_CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_TARGET}"
    export CGO_LDFLAGS="${LDFLAGS} --sysroot=${STAGING_DIR_TARGET}"
    ( cd ${WORKDIR}/build/src/${GO_IMPORT} && dep init && dep ensure -v && go build -i -o ${WORKDIR}/buildkite-agent . )
}

do_install() {
    install -d "${D}/${bindir}"
    install -m 0755 "${WORKDIR}/buildkite-agent" "${D}${bindir}/buildkite-agent"
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
RDEPENDS_${PN}-staticdev += "bash"
RDEPENDS_${PN}-dev += "bash"
