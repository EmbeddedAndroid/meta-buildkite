DESCRIPTION = "The Buildkite Agent is an open-source toolkit written in Golang for securely running build jobs on any device or network"
HOMEPAGE = "https://buildkite.com/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/src/${GO_IMPORT}/LICENSE.txt;md5=144ba5eac4d4147eb1d7c5f85cb1f67f"

SRC_URI = "git://${GO_IMPORT}"
SRCREV = "eecaae408f019e5a7c2724d607ba9b3c04a95bd9"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "github.com/buildkite/agent"
GO_INSTALL = "${GO_IMPORT}/buildkite-agent"

inherit go

# This is just to make clear where this example is
do_install_append() {
    mv ${D}${bindir}/buildkite-agent ${D}${bindir}/${BPN}
}
