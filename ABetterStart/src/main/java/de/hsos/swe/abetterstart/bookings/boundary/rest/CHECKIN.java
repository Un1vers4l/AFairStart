package de.hsos.swe.abetterstart.bookings.boundary.rest;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("CHECKIN")
public @interface CHECKIN {
}
