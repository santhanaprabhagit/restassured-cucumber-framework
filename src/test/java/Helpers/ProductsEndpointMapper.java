package Helpers;
import Managers.FileReaderManager;
public class ProductsEndpointMapper
    {
        public enum apiEndPointType
        {
            products,
            addProduct
        }

        public static String GetProductsEndPointUrl(apiEndPointType endpoint)
        {
            String endpointUrl = FileReaderManager.getInstance().getConfigFileReader().getUrl();;
            switch (endpoint)
            {
                case products:
                    endpointUrl += "/products/";
                    break;
                case addProduct:
                    endpointUrl += "/products/add";
                    break;
            }
            return endpointUrl;
        }
}
