package net.serialisation.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Created by greenlucky on 12/29/16.
 */
public class Book {

    ObjectFactory of;
    CatalogDataType cldts;

    public Book(){
        of = new ObjectFactory();
        cldts = new CatalogDataType();
    }

    public void make(Booksdata booksdata){
        Booksdata bsd = of.createBooksdata();
        bsd.setId(booksdata.getId());
        bsd.setTitle(booksdata.getTitle());
        bsd.setGenre(booksdata.getGenre());
        bsd.setPublishDate(booksdata.getPublishDate());
        bsd.setDescription(booksdata.getDescription());
        bsd.setPrice(booksdata.getPrice());

        cldts.getBook().add(bsd);
    }

    public void marshal(){
        try {
            JAXBElement<CatalogDataType> gl =
                    of.createCatalogData( cldts );
            JAXBContext jc = JAXBContext.newInstance("net.serialisation.data");
            Marshaller m = jc.createMarshaller();
            m.marshal( gl, System.out );
        } catch( JAXBException jbe ){
            System.out.println(jbe.getMessage());
        }
    }
}
