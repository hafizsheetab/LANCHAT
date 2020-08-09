
        package Server;

        import java.io.*;

        public class Storage {
            public static void storeObject(Object object,String objectName){
                OutputStream outputStream = null;
                ObjectOutputStream objectOutputStream = null;
                File file = new File(objectName + ".txt");
                try {
                    file.delete();

                }
                catch (Exception e){
                    System.out.println(e);
                }
                try{
                    outputStream = new FileOutputStream(objectName +".txt");
                    objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(object);
                    objectOutputStream.flush();

                }
                catch (Exception e){
                    System.out.println(e);
                }
                finally {
                    try{
                        if(objectOutputStream!=null){
                            objectOutputStream.close();
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }

            }
            public static Object getObject(String objectName){
                InputStream inputStream = null;
                ObjectInputStream objectInputStream = null;
                Object object = new Object();
                try{
                    inputStream  = new FileInputStream(objectName + ".txt");
                    objectInputStream = new ObjectInputStream(inputStream);
                    object = (Object)objectInputStream.readObject();


                }
                catch (Exception e){
                    System.out.println(e);

                }
                finally {
                    try {
                        if(objectInputStream!=null){
                            objectInputStream.close();
                        }

                    }
                    catch (Exception e){
                        System.out.println(e);

                    }
                }
                return object;

            }
        }