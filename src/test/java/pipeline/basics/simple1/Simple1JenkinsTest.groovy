package pipeline.basics.simple1

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.lesfurets.jenkins.unit.PipelineTestHelper
import org.junit.Before
import org.junit.Test

//@groovy.transform.InheritConstructors
class Simple1JenkinsTest extends BasePipelineTest {

    private PipelineTestHelper helper

    @Before
    public void beforeClass() throws Exception {
        super.setUp();
    }

    @Test
    void should_execute_without_errors() throws Exception {
        def script = loadScript("pipeline/basics/simple1/Jenkinsfile")
        script.execute()
        printCallStack()
    }

    @Test void should_not_leave_environment_dirty() throws Exception {
        runScript('pipeline/basics/simple1/Jenkinsfile')
        printCallStack()
        assertCallStack().contains('echo(SOMEVAR inside closure = SOMEVAL)')
        assertCallStack().contains('echo(SOMEVAR overlapping inside closure = SOMEVAL)')
        assertCallStack().contains('echo(SOMEVAR restored inside closure = SOMEVAL)')
        assertCallStack().contains('echo(SOMEVAR outside closure = null)')
    }

}
