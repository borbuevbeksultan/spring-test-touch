package kg.ice.config;

import kg.ice.annotation.DataSets;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class ServiceTestExecutionListener implements TestExecutionListener {

    private IDatabaseTester databaseTester;

    public void beforeTestClass(TestContext testContext) throws Exception {

    }

    public void prepareTestInstance(TestContext testContext) throws Exception {

    }

    public void beforeTestMethod(TestContext testContext) throws Exception {
        DataSets dataSetsAnnotation = testContext.getTestMethod().getAnnotation(DataSets.class);
        if (dataSetsAnnotation == null) {
            return;
        }
        String dataSetName = dataSetsAnnotation.setUpDataSet();
        if (!dataSetName.equals("")) {
            databaseTester = (IDatabaseTester) testContext.getApplicationContext().getBean("databaseTester");
            XlsDataFileLoader xlsDataFileLoader = (XlsDataFileLoader) testContext.getApplicationContext().getBean("xlsDataFileLoader");
            IDataSet dataSet = xlsDataFileLoader.load(dataSetName);
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
        }
    }

    public void afterTestMethod(TestContext testContext) throws Exception {
        if (databaseTester != null) {
            databaseTester.onTearDown();
        }
    }

    public void afterTestClass(TestContext testContext) throws Exception {

    }
}
