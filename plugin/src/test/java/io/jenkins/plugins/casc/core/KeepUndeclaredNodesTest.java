package io.jenkins.plugins.casc.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import hudson.model.Node;
import hudson.slaves.DumbSlave;
import hudson.slaves.JNLPLauncher;
import io.jenkins.plugins.casc.ConfigurationAsCode;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

/**
 * With {@code keepUndeclaredNodes}, {@code jenkins.nodes} adds and updates the nodes it declares and removes no others,
 * such as nodes created in the UI.
 */
@WithJenkins
class KeepUndeclaredNodesTest {

    private static final String PROPERTY = JenkinsConfigurator.class.getName() + ".keepUndeclaredNodes";

    @TempDir
    private File remoteFS;

    private JenkinsRule j;

    @BeforeEach
    void setUp(JenkinsRule j) {
        this.j = j;
        System.clearProperty(PROPERTY);
    }

    @AfterEach
    void clearProperty() {
        System.clearProperty(PROPERTY);
    }

    @Test
    void undeclaredNodesAreKeptWithTheFlag() throws Exception {
        System.setProperty(PROPERTY, "true");
        j.jenkins.addNode(agent("made-in-the-ui"));
        configure();
        assertNotNull(j.jenkins.getNode("declared"), "declared");
        assertNotNull(j.jenkins.getNode("made-in-the-ui"), "kept, although not declared");
    }

    @Test
    void declaredNodesAreUpdatedNotDuplicatedWithTheFlag() throws Exception {
        System.setProperty(PROPERTY, "true");
        j.jenkins.addNode(agent("declared"));
        configure();
        assertEquals(
                1,
                j.jenkins.getNodes().stream()
                        .filter(n -> n.getNodeName().equals("declared"))
                        .count(),
                "one node of that name");
        Node declared = j.jenkins.getNode("declared");
        assertNotNull(declared);
        assertEquals("/home/jenkins/declared", ((DumbSlave) declared).getRemoteFS(), "replaced by the declared one");
    }

    @Test
    void undeclaredNodesAreRemovedWithoutIt() throws Exception {
        j.jenkins.addNode(agent("made-in-the-ui"));
        configure();
        assertNotNull(j.jenkins.getNode("declared"));
        assertNull(j.jenkins.getNode("made-in-the-ui"), "replaced, as before");
    }

    private DumbSlave agent(String name) throws Exception {
        return new DumbSlave(name, remoteFS.getAbsolutePath(), new JNLPLauncher(true));
    }

    private void configure() throws Exception {
        ConfigurationAsCode.get()
                .configure(getClass().getResource("declared-node.yaml").toExternalForm());
    }
}
