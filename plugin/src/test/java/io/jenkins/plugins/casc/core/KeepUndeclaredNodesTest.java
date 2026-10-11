package io.jenkins.plugins.casc.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import hudson.slaves.DumbSlave;
import hudson.slaves.JNLPLauncher;
import io.jenkins.plugins.casc.ConfigurationAsCode;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

/**
 * With {@code keepUndeclaredNodes}, {@code jenkins.nodes} adds and updates the nodes it declares and removes no others,
 * e.g. nodes created in the UI, or by another controller sharing them.
 */
public class KeepUndeclaredNodesTest {

    private static final String PROPERTY = JenkinsConfigurator.class.getName() + ".keepUndeclaredNodes";

    @Rule
    public JenkinsRule j = new JenkinsRule();

    @After
    public void clearProperty() {
        System.clearProperty(PROPERTY);
    }

    @Test
    public void undeclaredNodesAreKeptWithTheFlag() throws Exception {
        System.setProperty(PROPERTY, "true");
        j.jenkins.addNode(new DumbSlave("made-in-the-ui", "/tmp/ui", new JNLPLauncher(true)));
        ConfigurationAsCode.get().configure(getClass().getResource("declared-node.yaml").toExternalForm());
        assertNotNull("declared", j.jenkins.getNode("declared"));
        assertNotNull("kept, although not declared", j.jenkins.getNode("made-in-the-ui"));
    }

    @Test
    public void undeclaredNodesAreRemovedWithoutIt() throws Exception {
        j.jenkins.addNode(new DumbSlave("made-in-the-ui", "/tmp/ui", new JNLPLauncher(true)));
        ConfigurationAsCode.get().configure(getClass().getResource("declared-node.yaml").toExternalForm());
        assertNotNull(j.jenkins.getNode("declared"));
        assertNull("replaced, as before", j.jenkins.getNode("made-in-the-ui"));
    }
}
