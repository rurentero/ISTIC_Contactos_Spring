package io.swagger.api;

import io.swagger.model.Contacto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-08T18:01:04.874Z[GMT]")
@Controller
public class ContactosApiController implements ContactosApi {

    private static final Logger log = LoggerFactory.getLogger(ContactosApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ContactosApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addContact(@ApiParam(value = "Contacto que se va a añadir a la lista" ,required=true )  @Valid @RequestBody Contacto body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteContact(@ApiParam(value = "",required=true) @PathVariable("contactId") Long contactId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Contacto> findClosest() {
        Contacto contacto = new Contacto();
        contacto.setId(Long.valueOf(1));
        contacto.setName("Francisco");
        contacto.setNumero("927112233");
        //TODO ubicacion
        log.info("Retornando contacto cercano");
        return new ResponseEntity<Contacto>(contacto, HttpStatus.OK);
    }

    public ResponseEntity<List<Contacto>> getContacts() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Contacto>>(objectMapper.readValue("[ {\n  \"ubicacion\" : {\n    \"latitud\" : 6.027456183070403,\n    \"longitud\" : 1.4658129805029452\n  },\n  \"numero\" : \"numero\",\n  \"name\" : \"name\",\n  \"id\" : 0\n}, {\n  \"ubicacion\" : {\n    \"latitud\" : 6.027456183070403,\n    \"longitud\" : 1.4658129805029452\n  },\n  \"numero\" : \"numero\",\n  \"name\" : \"name\",\n  \"id\" : 0\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Contacto>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Contacto>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
