#!/usr/bin/env node

const fs = require("fs").promises;

async function copyToDocs(demoName) {
    try {
        await fs.copyFile(`demos/${demoName}/README.md`, `docs/demo-${demoName}.md`);
    } catch(err) {
        console.log(err)
    }
}

fs.readdir('demos')
    .then(result => result
        .filter(demoName => demoName !== 'README.adoc')
        .forEach(copyToDocs))
    .then(() => console.log('Completed copying demos'))