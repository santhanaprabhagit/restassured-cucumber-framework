package Models;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AddNewProductReqModel {
        public String title;
        public String description;
        public int price;
        public double discountPercentage;
        public double rating;
        public int stock;
        public String brand;
        public String category;
        public String thumbnail;
        public ArrayList<String> images;

}
