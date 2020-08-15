package pipeline.basics.unittestable

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.lesfurets.jenkins.unit.PipelineTestHelper
import org.junit.Before
import org.junit.Test

class UnitTestableJenkinsTest extends BasePipelineTest {

    private PipelineTestHelper helper

    @Before
    public void beforeClass() throws Exception {
        super.setUp();
        helper.registerAllowedMethod("pipeline", [Closure.class], { it -> it() })
    }

    @Test
    void should_execute_without_errors() throws Exception {
        def script = loadScript("pipeline/basics/unittestable/Jenkinsfile")
        script.execute()
        printCallStack()
    }

    @Test void should_not_leave_environment_dirty() throws Exception {
        runScript('pipeline/basics/unittestable/Jenkinsfile')
        printCallStack()
    }
}
