const fs = require('fs')

const demos = fs.readdirSync('demos')
        .filter(demoName => demoName !== 'README.adoc')

module.exports = {
  someSidebar: {
    'JCasC development': ['developer', 'implementation-details'],
    'Plugin compatibility': ['plugin-compatibility'],
    'Plugin developers': ['plugin-developers', 'benchmarks'],
    Features: ['export', 'configuration-reload', 'secrets', 'seed-jobs'],
    Demos: demos,
    Tutorial: ['getting-started']
  },
};
