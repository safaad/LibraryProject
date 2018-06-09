package Menu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import BookStore.*;
import Individual.*;

public class files {
	private ObjectInputStream EmpRead;
	private FileInputStream EmpFileInput;
	private ObjectOutputStream EmpWrite;
	private FileOutputStream EmpFileOutput;

	private ObjectOutputStream PrWrite;
	private FileOutputStream PrFileOutput;
	private ObjectInputStream PrRead;
	private FileInputStream PrFileInput;

	private DataOutputStream sizeOutputStream;
	private DataInputStream sizeInputStream;
	
	private ObjectInputStream CRead;
	private FileInputStream CFileInput;
	private ObjectOutputStream CWrite;
	private FileOutputStream CFileOutput;

	public int sizeOfEmployees = 0;
	private int sizeOfBooks = 0;
	private int sizeOfRentedBooks = 0;
	private int sizeOfClients = 0;

	public boolean initializeSize() {
		Path p = Paths.get("Size.txt");
		if(Files.notExists(p))
			return false;
		try {
			FileInputStream k = new FileInputStream("Size.txt");
			sizeInputStream = new DataInputStream(k);

			sizeOfEmployees = sizeInputStream.readInt(); //employees
			sizeInputStream.readChar(); // reads the \n
			sizeOfClients = sizeInputStream.readInt(); // clients
			sizeInputStream.readChar(); // reads the \n
			sizeOfBooks = sizeInputStream.readInt(); // books
			sizeInputStream.readChar(); // reads the \n
			sizeOfRentedBooks = sizeInputStream.readInt(); // rented books
			sizeInputStream.readChar(); // reads the \n
			
			Employee.setSerial(sizeInputStream.readInt()); // Setting Employee serial to the on in the file
			sizeInputStream.readChar(); // reads the \n
			Client.setSerial(sizeInputStream.readInt()); // Setting Client serial to the one in the file
			sizeInputStream.readChar(); // reads the \n
			Transaction.setSerial(sizeInputStream.readInt()); // Setting Transaction IDs to the on in the file
			sizeInputStream.readChar(); // reads the \n
			if(sizeOfEmployees == 0 && sizeOfClients == 0 && sizeOfBooks == 0 && sizeOfRentedBooks == 0) {
				System.out.println("hello");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public void openEmployeeFileOutputMode() {
		try {
			EmpFileOutput = new FileOutputStream("Employees");
			EmpWrite = new ObjectOutputStream(EmpFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void savePerson() {
		try {
			EmpWrite.writeObject(Driver.empAm);
			EmpWrite.writeObject(Driver.empPm);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(Client c : Driver.clients)
			try {
				CWrite.writeObject(c);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		sizeOfClients = Driver.clients.size();
		sizeOfEmployees = 2;
		try {
			sizeOutputStream.writeInt(sizeOfEmployees); // size of employees = 2
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(sizeOfClients);
			sizeOutputStream.writeChar('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readPerson() {
		Object e = null;
		Path p = Paths.get("Employees"), p2 = Paths.get("Size.txt"), p3 = Paths.get("Clients");
		if (Files.notExists(p) || Files.notExists(p2) || Files.notExists(p3)) {
			return;
		}
		try {
			EmpFileInput = new FileInputStream("Employees");
			EmpRead = new ObjectInputStream(EmpFileInput);
			CFileInput = new FileInputStream("Clients");
			CRead = new ObjectInputStream(CFileInput);
			
			e = (Employee) EmpRead.readObject();
			Driver.empAm = (Employee) e;
			
			e = (Employee) EmpRead.readObject();
			Driver.empPm = (Employee) e;
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		for (int i = sizeOfClients; i > 0; i--) {
			try {
				e = (Client) CRead.readObject();
				Driver.clients.add((Client) e);
			} catch (ClassNotFoundException | IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void closeEmployeeFile() {
		try {
			if (EmpRead != null) {
				EmpRead.close();
				EmpFileInput.close();
			}
			if (EmpWrite != null) {
				EmpWrite.flush();
				EmpWrite.close();
				EmpFileOutput.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openProductsFile() {
		try {
			PrFileOutput = new FileOutputStream("Books");
			PrWrite = new ObjectOutputStream(PrFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveBooks() {
		for (Books b : Driver.books)
			try {
				PrWrite.writeObject(b);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		for (Books b : Driver.rentedBooks)
			try {
				PrWrite.writeObject(b);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			sizeOfBooks = Driver.books.size();
			sizeOfRentedBooks = Driver.rentedBooks.size();
			sizeOutputStream.writeInt(sizeOfBooks);
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(sizeOfRentedBooks);
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(Employee.getSerial());
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(Client.getSerial());
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(Transaction.getSerial());
			sizeOutputStream.writeChar('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void readBooks() {
		Object e = null;
		Path p = Paths.get("Books"), p2 = Paths.get("Size.txt");
		if (Files.notExists(p) || Files.notExists(p2)) {
			return;
		}
		try {
			PrFileInput = new FileInputStream("Books");
			PrRead = new ObjectInputStream(PrFileInput);
		} catch (IOException e1) {
			return;
		}
		for (int i = sizeOfBooks; i > 0; i--) {
			try {
				e = PrRead.readObject();
				if(e instanceof ForRent)
					Driver.books.add((ForRent) e);
				else
					Driver.books.add((Sale) e);
			} catch (ClassNotFoundException | IOException e2) {
				e2.printStackTrace();
			}
		}
		for (int i = sizeOfRentedBooks; i > 0; i--) {
			try {
				e = PrRead.readObject();
				if(e instanceof ForRent)
					Driver.rentedBooks.add((ForRent) e);
			} catch (ClassNotFoundException | IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void closeProductsFile() {
		try {
			if (PrRead != null) {
				PrRead.close();
				PrFileInput.close();
			}
			if (PrWrite != null) {
				PrWrite.flush();
				PrWrite.close();
				PrFileOutput.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openSizeFile() {
		try {
			FileOutputStream k = new FileOutputStream("Size.txt");
			sizeOutputStream = new DataOutputStream(k);
			//The file Size.txt will always start with 0\n0\n0\n
			/*sizeOutputStream.writeInt(0); // 0 employees
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(0); // 0 clients
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(0); // 0 books
			sizeOutputStream.writeChar('\n');
			sizeOutputStream.writeInt(0); // 0 rented books
			sizeOutputStream.writeChar('\n');
			
			sizeOutputStream.writeInt(100); //serial Employee
			sizeOutputStream.writeChar('\n'); // reads the \n
			sizeOutputStream.writeInt(500); // serial Client
			sizeOutputStream.writeChar('\n'); // reads the \n
			sizeOutputStream.writeInt(1); // serial Transaction
			sizeOutputStream.writeChar('\n'); // reads the \n*/
			} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSizeFile() {
		try {
			if(sizeInputStream != null) {
				sizeInputStream.close();
			}
			if (sizeOutputStream != null) {
				sizeOutputStream.flush();
				sizeOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openClientsFile(){
		try {
			CFileOutput = new FileOutputStream("Clients");
			CWrite = new ObjectOutputStream(CFileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeClientsFile() {
		try {
			if (CRead != null) {
				CRead.close();
				CFileInput.close();
			}
			if (CWrite != null) {
				CFileOutput.close();
				CWrite.flush();
				CWrite.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void read() {
		if(!initializeSize()) { // get the size of lists from the size.txt
			closeAllFiles();
			return;
		}
		readPerson(); // read persons to the list
		readBooks(); // read products to the list
		closeAllFiles(); // close the files and continue on with the program
	}

	public void save() {
		openAllFiles();
		savePerson();
		saveBooks();
		closeAllFiles();
	}

	public void openAllFiles() {
		openSizeFile();
		openEmployeeFileOutputMode();
		openProductsFile();
		openClientsFile();
	}

	public void closeAllFiles() {
		closeSizeFile();
		closeEmployeeFile();
		closeProductsFile();
		closeClientsFile();
	}

}
