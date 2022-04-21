package com;

import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
//For XML

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserService
{
         User userObj = new User();
         @GET
         @Path("/")
         @Produces(MediaType.TEXT_HTML)
         public String readItems()
         {
        	 return userObj.readItems();
         }
         
         
}