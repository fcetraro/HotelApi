package com.ml.HotelApi;

import com.ml.HotelApi.model.request.FullNewBookingDTO;
import com.ml.HotelApi.model.request.NewBookingDTO;
import com.ml.HotelApi.util.Assembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.ml.HotelApi.stubs.FullBookingStub.getFullNewBookingStub;
import static com.ml.HotelApi.stubs.NewBookingStub.getBookingStub;
import static com.ml.HotelApi.stubs.PaymentMethodStub.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotelApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	String url= "http://localhost:8080/api/v1/";

	@Test
	void shouldGetAllHotel() throws  Exception {

		String request =  url.concat("hotels");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void shouldGetBadRequestWrongDatesFormat() throws  Exception {
		String request =  url.concat("hotels?dateFrom=01-31-2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetHotelRoom() throws  Exception {
		String request =  url.concat("hotels?destination=Puerto Iguazú&dateFrom=05/10/2021&dateTo=09/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void shouldNotGetAvailableHotelDestinationNotFound() throws  Exception {
		String request =  url.concat("hotels?destination=Puerto Iguazúiii&dateFrom=05/10/2021&dateTo=09/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void shouldGetFilterNotFound() throws  Exception {
		String request =  url.concat("hotels?lateCheckOut=true&dateFrom=05/10/2021&dateTo=07/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void shouldGetBadRequestWrongDates() throws  Exception {
		String request =  url.concat("hotels?destination=Puerto Iguazú&dateFrom=05/10/2021&dateTo=03/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetBadRequestWrongEmail() throws  Exception {
		String request =  url.concat("booking");
		FullNewBookingDTO stubBooking = getFullNewBookingStub();
		stubBooking.setUserName("wrongemail");
		String requestJson = "{"+ Assembler.transformToStringJson(stubBooking)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetBadRequestWrongCreditCard() throws  Exception {
		String request =  url.concat("booking");
		FullNewBookingDTO stubFullBooking = getFullNewBookingStub();
		NewBookingDTO stubBooking = getBookingStub();
		stubBooking.setPaymentMethod(getInvalidCreditPaymentMethod());
		stubFullBooking.setBooking(stubBooking);
		String requestJson = "{"+ Assembler.transformToStringJson(stubFullBooking)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetBadRequestWrongDebitCard() throws  Exception {
		String request =  url.concat("booking");
		FullNewBookingDTO stubFullBooking = getFullNewBookingStub();
		NewBookingDTO stubBooking = getBookingStub();
		stubBooking.setPaymentMethod(getInvalidDebitPaymentMethod());
		stubFullBooking.setBooking(stubBooking);
		String requestJson = "{"+ Assembler.transformToStringJson(stubFullBooking)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldBookDebitCard() throws  Exception {
		String request =  url.concat("booking");
		FullNewBookingDTO stubFullBooking = getFullNewBookingStub();
		NewBookingDTO stubBooking = getBookingStub();
		stubBooking.setPaymentMethod(getDebitPaymentMethod());
		stubFullBooking.setBooking(stubBooking);
		String requestJson = "{"+ Assembler.transformToStringJson(stubFullBooking)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
