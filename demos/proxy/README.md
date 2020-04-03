---
id: proxy
title: Proxy
sidebar_label: Proxy
---

# Configure proxy

## Sample configuration

```yaml
jenkins:
  proxy:
    name: "proxyhost"
    port: 80
    userName: "login"
    secretPassword: "password"
    noProxyHost: "externalhost"
    testUrl: "http://google.com"
```
