import nl.hu.iac.wsinterface.Fault;
import nl.hu.iac.wsinterface.MeanRequest;
import nl.hu.iac.wsinterface.MeanResponse;
import nl.hu.iac.wsinterface.MeanServiceInterface;
import nl.hu.iac.wsmean.MeanServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


/**
 * Created by Philip on 3/12/2017.
 */
public class MeanServiceImplTest {
    MeanServiceInterface meanService;

    @org.junit.Before
    public void setUp() throws Exception {
        meanService = new MeanServiceImpl();
    }

    @org.junit.After
    public void destroy() throws Exception {

    }

    @Test
    public void regularRequestTest() throws Exception {
        MeanRequest mreq = new MeanRequest();
        mreq.getValuelist().add(BigInteger.valueOf(1));
        mreq.getValuelist().add(BigInteger.valueOf(1));

        MeanResponse mresp = meanService.calculateMean(mreq);

        assertNotNull("Response is null!", mresp);
        assertEquals("output is not expected value!", BigDecimal.valueOf(1.0), mresp.getMean());
    }

    @Test
    public void negativeRequestTest() throws Exception {
        MeanRequest mreq = new MeanRequest();
        mreq.getValuelist().add(BigInteger.valueOf(-1));
        mreq.getValuelist().add(BigInteger.valueOf(-1));

        MeanResponse mresp = meanService.calculateMean(mreq);

        assertNotNull("Response is null!", mresp);
        assertEquals("output is not expected value!", BigDecimal.valueOf(-1.0), mresp.getMean());
    }

    @Test
    public void largeRequestTest() throws Exception {
        MeanRequest mreq = new MeanRequest();
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));
        mreq.getValuelist().add(BigInteger.valueOf(1000000));

        MeanResponse mresp = meanService.calculateMean(mreq);

        assertNotNull("Response is null!", mresp);
        assertEquals("output is not expected value!", BigDecimal.valueOf(1000000.0), mresp.getMean());
    }

    @Test(expected = Fault.class)
    public void emptyRequestTest() throws Exception {
        MeanRequest mreq = new MeanRequest();
        MeanResponse mresp = meanService.calculateMean(mreq);
    }
}
