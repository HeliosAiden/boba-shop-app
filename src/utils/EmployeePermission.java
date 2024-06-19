package utils;

public enum EmployeePermission {

    STAFF("staff", "Nhân viên", 2),
    MANAGER("manager", "Quản lý", 1),
    INACTIVE("inactive", "Nghỉ việc", 0);
    private final String id, name;
    private final int priority;

    EmployeePermission(String id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    public static EmployeePermission getById(String id) {
        for (EmployeePermission e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return STAFF;
    }

    public static EmployeePermission getByName(String name) {
        for (EmployeePermission e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return STAFF;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int compare(EmployeePermission other) {
        return priority - other.priority;
    }
}
