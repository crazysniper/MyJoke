package application.fourmodule.ui.contentprovider.bean;

import java.util.List;

public class ContactBean {
    private long id;
    private String name;
    private List<Phone> phoneList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public static class Phone {
        private long id;
        private String number;
        private int type;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
