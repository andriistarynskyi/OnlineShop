package service;

import entity.Customer;
import entity.Gender;
import repository.CustomerRepository;
import utils.Constant;
import utils.DateParser;
import utils.FileReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    CustomerRepository customerRepository = new CustomerRepository();

    public List<Customer> parseCustomersFromFile() {
        String customersFilePath = Constant.CUSTOMERS_FILE_PATH;
        List<Customer> customersList = new ArrayList<>();
        List<String> customersDataList = FileReader.read(customersFilePath);

        for (String str : customersDataList) {
            String[] tempArray = str.split(";");

            String customerName = tempArray[0];
            LocalDate dateOfBirth = DateParser.parse(tempArray[1], Constant.DOB_PATTERN);
            String address = tempArray[2];
            Gender gender = getGender(tempArray[3]);
            String phoneNumber = getPhoneNumber(tempArray[4]);

            customersList.add(new Customer(customerName, dateOfBirth, address, gender, phoneNumber));

        }
        return customersList;
    }

    private Gender getGender(String str) {
        Gender gender = null;
        if (str.equals("Male") || (str.equals("male"))) {
            gender = Gender.MALE;
        } else if (str.equals("Female") || (str.equals("female"))) {
            gender = Gender.FEMALE;
        }
        return gender;
    }

    public String getPhoneNumber(String str) {
        String phoneNumber = "";
        if (!str.equals("")) {
            phoneNumber = str;
        } else {
            phoneNumber = "";
        }
        return phoneNumber;
    }

    public boolean save(Customer customer) {
        customerRepository.save(customer);
        return true;
    }

    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    public Customer getByName(String name) {
        return customerRepository.getByName(name);
    }
}