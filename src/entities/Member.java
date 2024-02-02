package entities;

public class Member {
    private String name;
    private String role;

    public Member() {
    }
    public Member(String nam, String rol) {
        name = nam;
        role = rol;
    }

    public static void main(String[] args) {
        String testName = "Jane Doe";
        String testRole = "Tester";
        Member member = new Member(testName, testRole);

        assert testName.equals(member.name): "Setter for name is not working correctly!";
        assert testRole.equals(member.role): "Setter for role is not working correctly!";

        // if both strings are empty
        member = new Member("", "");
        assert "".equals(member.name): "Setter for name is not working correctly if it is an empty string!";
        assert "".equals(member.role): "Setter for role is not working correctly! if it is an empty string";
    }
}
