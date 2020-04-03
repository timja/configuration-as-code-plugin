module.exports = {
  title: 'Jenkins Configuration as Code plugin',
  tagline: 'Configure Jenkins based on human-readable declarative configuration files.',
  url: 'https://jenkins-configuration-as-code.netlify.com/',
  baseUrl: '/',
  favicon: 'img/favicon.ico',
  organizationName: 'jenkinsci', // Usually your GitHub org/user name.
  projectName: 'configuration-as-code-plugin', // Usually your repo name.
  themeConfig: {
    navbar: {
      title: 'Home',
      logo: {
        alt: 'Configuration as Code plugin',
        src: 'img/logo.svg',
      },
      links: [
        {
          to: 'docs/export',
          label: 'Docs',
          position: 'left',
        },
        {
          to: 'docs/active-directory',
          label: 'Demos',
          position: 'left',
        },
        {
          to: 'docs/getting-started',
          label: 'Tutorial',
          position: 'left',
        },
        {to: 'blog', label: 'Blog', position: 'left'},
        {
          href: 'https://github.com/jenkinsci/configuration-as-code-plugin',
          label: 'GitHub',
          position: 'left',
        },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Community',
          items: [
            {
              label: 'Gitter',
              href: 'https://gitter.im/jenkinsci/configuration-as-code-plugin',
            },
          ],
        },
        {
          title: 'Social',
          items: [
            {
              label: 'Blog',
              to: 'blog',
            },
            {
              label: 'GitHub',
              href: 'https://github.com/jenkinsci/configuration-as-code-plugin',
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()}. Built with Docusaurus.`,
    },
  },
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl:
            'https://github.com/jenkinsci/configuration-as-code-plugin/edit/master/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      },
    ],
  ],
};
