/**
 * @author Rajat Verma
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Selenium Java Test Framework & Best Practices - Masterclass (https://www.udemy.com/course/selenium-java-test-framework/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package org.selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.api.ApiRequest;
import org.selenium.constants.FrameworkConstants;
import org.selenium.utils.VerificationManager;

import java.util.HashMap;

public class CartApi {

	private Cookies cookies;

	public Cookies getCookies() {
		return cookies;
	}

	public void setCookies(Cookies cookies) {
		this.cookies = cookies;
	}

	public CartApi() {
		super();
	}

	public CartApi(Cookies cookies) {
		super();
		this.cookies = cookies;
	}

	@Override
	public String toString() {
		return "CartApi [cookies=" + cookies + "]";
	}

	public Response addToCart(int productId, int quantity) {
		Header header = new Header("content-type", "application/x-www-form-urlencoded");
		Headers headers = new Headers(header);

		HashMap<String, Object> formParams = new HashMap<>();
		formParams.put("product_sku", "");
		formParams.put("product_id", productId);
		formParams.put("quantity", quantity);

		/* If the Customer is not logged in, then only create the Object of Cookies */
		/* If the Customer is logged in, then use the already created Cookies */
		if (cookies == null) {
			cookies = new Cookies();
		}

//		Response response = 
//				given().
//					baseUri(ConfigLoader.getInstance().getBaseUrl()).
//					headers(headers).
//					formParams(formParams).
//					cookies(cookies).
//					log().all().
//				when().
//					post("/?wc-ajax=add_to_cart").
//				then().
//					log().all().
//					extract().response();

		Response response=ApiRequest.post("/?wc-ajax=add_to_cart", headers, formParams, cookies);		
		
		VerificationManager.validateResponse(response.getStatusCode(),200, 
				  FrameworkConstants.ASSERTION_FOR_RESPONSE_STATUS_CODE +" -  <b> <u> Add the product to the Cart </b> </u>");
		
		this.cookies = response.getDetailedCookies();
		return response;

	}
}
