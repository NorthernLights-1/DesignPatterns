package dataaccessobject;

import java.io.*;
import java.util.Base64;

public class CustomerDaoImpl implements CustomerDao{

    private String filePath = new String("./Customer.dat");

    @Override
    public Customer getCustomer() {
        try{
            FileInputStream in = new FileInputStream(filePath);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            String str = new String(buffer, "UTF-8");
            Customer cus = (Customer)fromString(str);
            return cus;
        } catch (ClassNotFoundException|IOException e) {
            return null;
        }

    }


    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            String str=toString((Serializable) customer);
            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(str.getBytes());
            fileOut.close();
            System.out.println("Customer data is saved");
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    private  Object fromString( String s) throws IOException, ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    private String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

}
