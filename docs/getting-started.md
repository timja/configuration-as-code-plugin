---
id: getting-started
title: Getting started
sidebar_label: Getting started
---

## Getting Started

First, start a Jenkins instance with the [Configuration as Code](https://plugins.jenkins.io/configuration-as-code) plugin installed.

- Those running Jenkins as a [Docker](https://github.com/jenkinsci/docker) container (and maybe also [pre-installing plugins](https://github.com/jenkinsci/docker#preinstalling-plugins)), do include [Configuration as Code](https://plugins.jenkins.io/configuration-as-code) plugin.

Second, the plugin looks for the `CASC_JENKINS_CONFIG` environment variable. The variable can point to any of the following:

- Path to a folder containing a set of config files. For example, `/var/jenkins_home/casc_configs`.
- A full path to a single file. For example, `/var/jenkins_home/casc_configs/jenkins.yaml`.
- A URL pointing to a file served on the web. For example, `https://acme.org/jenkins.yaml`.

If `CASC_JENKINS_CONFIG` points to a folder, the plugin will recursively traverse the folder to find file (suffix with .yml,.yaml,.YAML,.YML), but doesn't contain hidden files or hidden subdirectories. It doesn't follow symbolic links.

If you do not set the `CASC_JENKINS_CONFIG` environment variable, the plugin will
default to looking for a single config file in `$JENKINS_HOME/jenkins.yaml`.

If everything was setup correctly, you should now be able to browse the Configuration as Code page with `Manage Jenkins` -> `Configuration as Code`.

## Initial Configuration

When configuring the first Jenkins instance, browse the examples shown in the [demos](demos)
directory of this repository. If you have a plugin that does not have an example, consult the reference
help document. Click the `Documentation` link at the bottom of the Configuration as Code page.

![Reference Page](images/reference.png)

If you want to configure a specific plugin, search the page for the name of the plugin. The page will
show you which root element belongs to the configuration. Most installed plugins belong under the `unclassified` root
element.

![Unclassified Section](images/unclassified.png)

## Examples

### LDAP

Replacing user interface based configuration for LDAP with the text based configuration.

![configuration form](images/sample_form.png)

```yaml
jenkins:
  securityRealm:
    ldap:
      configurations:
        - groupMembershipStrategy:
            fromUserRecord:
              attributeName: "memberOf"
          inhibitInferRootDN: false
          rootDN: "dc=acme,dc=org"
          server: "ldaps://ldap.acme.org:1636"
```

Also see [demos](demos) folder with various samples.

## Documentation

<a name="handling-secrets"></a>

You can find more documentation about JCasC here:

- [Handling Secrets](./docs/features/secrets.adoc)
- [Exporting configurations](./docs/features/configExport.md)
- [Validating configurations](./docs/features/jsonSchema.md)
- [Triggering Configuration Reload](./docs/features/configurationReload.md)

The configuration file format depends on the version of jenkins-core and installed plugins.
Documentation is generated from a live instance, as well as a JSON schema you can use to validate configuration file
with your favourite YAML tools.

The JSON Schema documentation can be found [here](./docs/features/jsonSchema.md).

**TODO**: Provide a Dockerfile to generate documentation from specified jenkins-core release and plugins.

## Installing plugins

We don't support installing plugins with JCasC you need to use something else for this,

Dockers users can use:\
[https://github.com/jenkinsci/docker/#preinstalling-plugins](https://github.com/jenkinsci/docker/#preinstalling-plugins)

Kubernetes users:\
[https://github.com/helm/charts/tree/master/stable/jenkins](https://github.com/helm/charts/tree/master/stable/jenkins)