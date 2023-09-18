package StepDefinitions;
import Helpers.ApiAccessHelper;
import Helpers.ProductsEndpointMapper;
import Models.AddNewProductReqModel;
import Models.Products;
import Models.ProductsListRespModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.Method;
import org.testng.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductApiSteps {
    private ApiAccessHelper apiAccessHelper = new ApiAccessHelper();
    private String url = "";
    private AddNewProductReqModel addNewProductReqModel;
    @And("The returned response is not empty")
    public void theReturnedResponseIsNotEmpty() throws IOException {
        String responseJson = ApiAccessHelper.lastResponse.asString().replaceAll("^\"|\"$", "");
        Gson gson = new GsonBuilder().create();
        ProductsListRespModel productsListResponse = gson.fromJson(responseJson, ProductsListRespModel.class);
        long numOfProducts = productsListResponse.total;
        List<Products> productsList = productsListResponse.products;
        for (Products products: productsList)
        {
            Assert.assertTrue(numOfProducts > 90, "Total number of product is not more than 90");
            Assert.assertNotNull(products.id, String.format("Id for product {0} is unexpectedly Null", products.id));
            Assert.assertNotNull(products.title, String.format("Title for product {0} is unexpectedly Null", products.title));
            Assert.assertNotNull(products.description, String.format("Description for product {0} is unexpectedly Null", products.description));
            Assert.assertNotNull(products.price, String.format("Price for product {0} is unexpectedly Null", products.price));
            //Can add more validation based on the expectation
        }
    }
    @Given("Send Request to get list of Products")
    public void sendRequestToGetListOfProducts() {
        url = ProductsEndpointMapper.GetProductsEndPointUrl(ProductsEndpointMapper.apiEndPointType.products);
        apiAccessHelper.sendRequest(url, Method.GET, null, null, null);
    }

    @Given("Send Request to add a new product")
    public void send_request_to_add_a_new_product(DataTable table) {
        String json = "";
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        AddNewProductReqModel addNewProductReqModel = new AddNewProductReqModel();
        for(Map<String, String> data : rows) {
            addNewProductReqModel.setTitle(data.get("title"));
            addNewProductReqModel.setDescription(data.get("description"));
            addNewProductReqModel.setPrice(Integer.parseInt(data.get("price")));
            addNewProductReqModel.setDiscountPercentage(Integer.parseInt(data.get("discountPercentage")));
            addNewProductReqModel.setRating(Double.parseDouble(data.get("rating")));
            addNewProductReqModel.setStock(Integer.parseInt(data.get("stock")));
            addNewProductReqModel.setBrand(data.get("brand"));
            addNewProductReqModel.setCategory(data.get("category"));
            addNewProductReqModel.setThumbnail(data.get("thumbnail"));
            addNewProductReqModel.setImages(new ArrayList<>(Arrays.asList(data.get("images"))));
        }
            url = ProductsEndpointMapper.GetProductsEndPointUrl(ProductsEndpointMapper.apiEndPointType.addProduct);
            Gson gson = new Gson();
            json = gson.toJson(addNewProductReqModel);
            apiAccessHelper.sendRequest(url, Method.POST, null, null, json);
    }
}


