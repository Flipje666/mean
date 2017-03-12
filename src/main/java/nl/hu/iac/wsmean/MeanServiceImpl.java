package nl.hu.iac.wsmean;

import com.sun.xml.ws.developer.SchemaValidation;
import nl.hu.iac.wsinterface.*;

import javax.jws.WebService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Gemaakt door Philip op 12/3/2017.
 */
@WebService(endpointInterface = "nl.hu.iac.wsinterface.MeanServiceInterface")
@SchemaValidation(handler = SchemaValErrorHandler.class)
public class MeanServiceImpl implements MeanServiceInterface {

    @Override
    public MeanResponse calculateMean(MeanRequest request) throws Fault {
        List<BigInteger> vals = request.getValuelist();
        ObjectFactory of = new ObjectFactory();
        MeanResponse res = of.createMeanResponse();

        try {
            int sum = 0;
            for (int i = 0;i < vals.size();i++) {
                sum += vals.get(i).intValue();
            }

            double meandb = (sum / vals.size());
            BigDecimal mean = BigDecimal.valueOf(meandb);
            res.setMean(mean);
        } catch (RuntimeException e) {
            MeanFault mf = of.createMeanFault();
            mf.setErrorCode((short) 1);
            mf.setMessage("Kan het gemiddelde van " + request.getValuelist() + " niet berekenen");
            Fault fault = new Fault("Het berekenen van het gemiddelde is mislukt.", mf);
            throw fault;
        }
        return res;
    }
}
