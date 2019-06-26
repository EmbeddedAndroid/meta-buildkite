DESCRIPTION = "The Buildkite Agent is an open-source toolkit written in Golang for securely running build jobs on any device or network"
HOMEPAGE = "https://buildkite.com/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LICENSE.txt;md5=0835ade698e0bcf8506ecda2f7b4f302"

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
