package de.hsos.swe.afairstart.bookings.boundary.rest;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("CHECKOUT")
public @interface CHECKOUT {
}
