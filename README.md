# meta-buildkite
OpenEmbedded/Yocto Project layer for the Buildkite Agent

## usage
At a minimum, add your buildkite-agent token to ```/etc/buildkite/buildkite-agent.cfg```

### enable buildkite-agent service
```sudo systemctl enable buildkite-agent.service```

### start buildkite-agent service
```sudo systemctl start buildkite-agent.service```

### stop buildkite-agent service
```sudo systemctl stop buildkite-agent.service```

### disable buildkite-agent service
```sudo systemctl disable buildkite-agent.service```

### loggging buildkite-agent service
```sudo journalctl -fu buildkite-agent```
