/**
 * @author butrim
 */
public class Main {
    public static void main(String[] args){
        TaskManager taskManager = Managers.taskManager();

/*        // option 1: nullable id
        SingleTask singleTask = new SingleTask(1, "Pure task", Status.NEW);
        taskManager.saveNewTask(singleTask);*/

        // option 2: no id at all
        SingleTask.ToCreate singleTaskToCreate = new SingleTask.ToCreate("Single safe task"); // id: 0
        taskManager.saveNewTask(singleTaskToCreate);

        taskManager.saveNewTask(new SingleTask.ToCreate("Another single safe task"));

        SingleTask singleTask = (SingleTask) taskManager.getTaskById(0);
        System.out.println(taskManager.getTaskById(0));

        // how to change status
        taskManager.update(singleTask.withStatus(Status.IN_PROGRESS));

        // 1: task by id
        singleTask = ((SingleTask) taskManager.getTaskById(0));
        // 2: set status on object not from hashmap
        singleTask.withStatus(Status.DONE);

        System.out.println(taskManager.getTaskById(0));

        System.out.println(Managers.taskManager().getTaskById(0)); // ?

        System.out.println("Same object?: " + Managers.taskManager().equals(Managers.taskManager()));
    }
}
