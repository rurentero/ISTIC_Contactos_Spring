package io.swagger.api;

import io.swagger.model.Contacto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Ubicacion;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-08T18:01:04.874Z[GMT]")
@Controller
public class ContactosApiController implements ContactosApi {

    private static final Logger log = LoggerFactory.getLogger(ContactosApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    //Mocked contact list
    private List<Contacto> agenda = new LinkedList<Contacto>();

    @org.springframework.beans.factory.annotation.Autowired
    public ContactosApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addContact(@ApiParam(value = "Contacto que se va a añadir a la lista" ,required=true )  @Valid @RequestBody Contacto body) {
        if (agenda.contains(body)) {
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }else {
            agenda.add(body);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Void> deleteContact(@ApiParam(value = "",required=true) @PathVariable("contactId") Long contactId) {
        boolean deleted = false;
        for (Contacto c : agenda) {
            if(c.getId().equals(contactId)) {
                agenda.remove(c);
                deleted=true;
            }
        }
        if (deleted)
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Contacto> findClosest() {
        // Mock implementation
        Contacto contacto = new Contacto();
        contacto.setId(Long.valueOf(1));
        contacto.setName("Francisco");
        contacto.setNumero("927112233");
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setLatitud(BigDecimal.valueOf(39.479483));
        ubicacion.setLongitud(BigDecimal.valueOf(-6.342079));
        contacto.setUbicacion(ubicacion);
        log.info("Retornando contacto cercano");
        return new ResponseEntity<Contacto>(contacto, HttpStatus.OK);
    }

    public ResponseEntity<List<Contacto>> getContacts() {
        return new ResponseEntity<List<Contacto>>(agenda, HttpStatus.OK);
    }

}
