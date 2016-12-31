package net.serialisation.data;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by greenlucky on 12/29/16.
 */

public class App {

    public static void main(String[] args){

        Booksdata booksdata1 = new Booksdata();
        booksdata1.setId(123456789);
        booksdata1.setTitle("Spring boot framework");
        booksdata1.setGenre("Programming");
        booksdata1.setPrice(9.9);
        XMLGregorianCalendar date2 = new XMLGregorianCalendarImpl();
        date2.setYear(2016);
        date2.setMonth(01);
        date2.setDay(20);
        booksdata1.setPublishDate(date2);
        booksdata1.setDescription("Getting start with Spring framework tutorial");

        Book book = new Book();
        book.make(booksdata1);
        book.marshal();
    }
}
