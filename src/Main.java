import org.json.JSONArray;
import org.json.JSONObject;
import Model.ResponModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstConnect {
    public static void main(String[] args) throws IOException {
        ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL("https://dummyjson.com/products/search?q=Laptop");
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);
        System.out.println(response);


        assert response != null;
        JSONArray responseJSON = new JSONArray(response);
        ArrayList<ResponModel> responModel = new ArrayList<>();
        for (int i = 0; i < responseJSON.length(); i++) {
            ResponModel resModel = new ResponModel();
            JSONObject myJSONObject = responseJSON.getJSONObject(i);
            resModel.setMsg(myJSONObject.getString("message"));
            resModel.setStatus(myJSONObject.getString("status"));
            resModel.setComment(myJSONObject.getString("comment"));
            responModel.add(resModel);
        }

        System.out.println("Response Are: ");
        for (int ind = 0; ind < responModel.size(); ind++) {
            System.out.println("MESSAGE : " + responModel.get(ind).getMsg());
            System.out.println("STATUS : " + responModel.get(ind).getStatus());
            System.out.println("COMMENTS : " + responModel.get(ind).getComment());
        }


        selectionSort(responModel);

        System.out.println("Response After Sorting: ");
        for (int ind = 0; ind < responModel.size(); ind++) {
            System.out.println("MESSAGE : " + responModel.get(ind).getMsg());
            System.out.println("STATUS : " + responModel.get(ind).getStatus());
            System.out.println("COMMENTS : " + responModel.get(ind).getComment());
        }


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the rating you want to search: ");
        int searchRating = scanner.nextInt();


        ArrayList<ResponModel> searchResults = searchByRating(responModel, searchRating);


        System.out.println("Search Results for Rating " + searchRating + ": ");
        if (searchResults.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (ResponModel result : searchResults) {
                System.out.println("MESSAGE : " + result.getMsg());
                System.out.println("STATUS : " + result.getStatus());
                System.out.println("COMMENTS : " + result.getComment());
            }
        }
    }

    private static void selectionSort(ArrayList<ResponModel> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).getMsg().compareTo(arr.get(minIndex).getMsg()) < 0) {
                    minIndex = j;
                }
            }
            ResponModel temp = arr.get(minIndex);
            arr.set(minIndex, arr.get(i));
            arr.set(i, temp);
        }
    }

    private static ArrayList<ResponModel> searchByRating(ArrayList<ResponModel> arr, int rating) {
        ArrayList<ResponModel> searchResults = new ArrayList<>();
        for (ResponModel model : arr) {
            // Assuming 'rating' is an integer value in the ResponModel class
            if (model.getRating() == rating) {
                searchResults.add(model);
            }
        }
        return searchResults;
    }
}