#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <stdbool.h>
#include <stdlib.h>
#include <direct.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <windows.h>
#include <time.h>
#define x strcmp(entry->d_name,
#define y !=0
int global (int argc , char *const argv[]) {
    if (strcmp (argv[3] , "user.name") == 0) {
        if (argc == 4) {
            perror ("please enter an username\n");
            return 1;
        }
        FILE *global;
        global = fopen ("global/user.name.txt" , "w");
        fprintf (global , "%s", argv[4]);
        fclose (global);
    } else if (strcmp (argv[3] , "user.email") == 0) {
        if (argc == 4) {
            perror ("please enter an email\n");
            return 1;
        }
        FILE *global;
        global = fopen ("global/user.email.txt" , "w");
        fprintf (global , "%s", argv[4]);
        fclose (global);
    } else {
        perror ("invalid command\n");
        return 1;
    }
    return 0;
}
int local (int argc , char *const argv[]) {
    if (strcmp (argv[2] , "user.name") == 0) {
        if (argc == 3) {
            perror ("please enter an username\n");
            return 1;
        }
        FILE *local;
        local = fopen (".main/local/user.name.txt" , "w");
        fprintf (local , "%s", argv[3]);
        fclose (local);
    } else if (strcmp (argv[2] , "user.email") == 0) {
        if (argc == 3) {
            perror ("please enter an email\n");
            return 1;
        }
        FILE *local;
        local = fopen (".main/local/user.email.txt" , "w");
        fprintf (local , "%s", argv[3]);
        fclose (local);
    } else {
        perror ("invalid command\n");
    }
    return 0;
}
void make_files_init2 (int argc , char *const argv[]) {
    FILE *file;
    CreateDirectory ("stages" , NULL);
    CreateDirectory ("commits" , NULL);
    file = fopen ("commits/commitnumber", "w");
    fclose (file);
}
void make_files_init1 (int argc , char *const argv[]) {
    CreateDirectory ("local" , NULL);
}
int init (int argc , char *const argv[]) {
    char cwd[FILENAME_MAX];
    getcwd (cwd , sizeof(cwd));
    char temp_dir[FILENAME_MAX];
    bool exist = false;
    struct dirent *entry;
    do {
        DIR *dir = opendir (cwd);
        while ((entry = readdir (dir)) != NULL) {
            if (entry->d_type == DT_DIR && strcmp (entry->d_name , ".main") == 0) {
                exist = true;
            }
        }
        getcwd (temp_dir , sizeof(temp_dir));
        if (strcmp (temp_dir , "D:\\") != 0) {
            if (chdir ("..") != 0) {
                return 1;
            }
        }
    } while (strcmp (temp_dir , "D:\\") != 0);
    chdir (cwd);
    if (exist) {
        perror ("the repository already exists!\n");
    } else {
        CreateDirectory (".main" , NULL);
        chdir (".main");
        make_files_init1 (argc , argv);
        CreateDirectory ("master" , NULL);
        chdir ("master");
        make_files_init2 (argc , argv);
        chdir ("..");
        chdir ("..");
    }
    return 0;
}
int addtostagef (int argc , char *const argv[]) {
    for (int p = 3; p < argc; p++) {
        DIR *dir = opendir (".");
        char fileaddress[FILENAME_MAX] , fileinput[FILENAME_MAX];
        struct dirent *entry;
        while ((entry = readdir (dir)) != NULL) {
            if (x".")y && x"..")y && x".git")y && x".main")y && x".vscode")y && x"global")y && x"main.c")y && x"main.exe")y && x"README.md")y && x"tempCodeRunnerFile.c")y ) {
                if (entry->d_type == DT_DIR && strcmp (entry->d_name , argv[p]) == 0) {
                    DIR *dir1 = opendir (".main/master/stages");
                    chdir (".main/master/stages");
                    struct dirent *entry1;
                    entry1 = readdir (dir1);
                    do {
                        if (entry1->d_type == DT_DIR && strcmp (entry1->d_name , argv[p]) == 0) {
                            DIR *dir2 = opendir (argv[p]);
                            chdir (argv[p]);
                            struct dirent *entry2;
                            while ((entry2 = readdir (dir2)) != NULL) {
                                remove (entry2->d_name);
                            }
                            chdir ("..");
                            rmdir (argv[p]);
                        }
                    } while ((entry1 = readdir (dir1)) != NULL);
                    CreateDirectory (argv[p] , NULL);
                    chdir (argv[p]);
                    getcwd (fileaddress , sizeof (fileaddress));
                    chdir ("..");
                    chdir ("..");
                    chdir ("..");
                    chdir ("..");
                    DIR *dir11 = opendir (argv[p]);
                    chdir (argv[p]);
                    getcwd (fileinput , sizeof (fileinput));
                    struct dirent *entry11;
                    while ((entry11 = readdir (dir11)) != NULL) {
                        FILE *file1 = fopen (entry11->d_name , "r");
                        if (file1 != NULL) {
                            chdir (fileaddress);
                            char name_temp[100];
                            FILE *file2 = fopen (entry11->d_name , "w");
                            char ch = getc (file1);
                            while (ch != EOF) {
                                putc (ch , file2);
                                ch = getc (file1);
                            }
                            chdir (fileinput);
                            fclose (file2);
                        }
                        fclose (file1);
                    }
                    chdir ("..");
                } else if (strcmp (argv[p] , entry->d_name) == 0) {
                    FILE *file1 , *file2;
                    file1 = fopen (argv[p] , "r");
                    chdir (".main");
                    chdir ("master");
                    chdir ("stages");
                    file2 = fopen (argv[p] , "w");
                    char ch = getc (file1);
                    while (ch != EOF) {
                        putc (ch , file2);
                        ch = getc (file1);
                    }
                    fclose (file1);
                    fclose (file2);
                    for (int i = 0; i < 3; i++) {
                        chdir ("..");
                    }
                }
            }
        }
        int flag = 0;
        DIR *dir13 = opendir (".");
        struct dirent *entry13;
        while ((entry13 = readdir (dir13)) != NULL) {
            if (strcmp (argv[p] , entry13->d_name) == 0) {
                flag = 1;
            }
        }
        if (flag == 0) {
            printf ("%s is not existed.\n", argv[p]);
        }
    }
    return 0;
}
int addtostage (int argc , char *const argv[]) {
        DIR *dir = opendir (".");
        char fileaddress[FILENAME_MAX] , fileinput[FILENAME_MAX];
        struct dirent *entry;
        while ((entry = readdir (dir)) != NULL) {
            if (x".")y && x"..")y && x".git")y && x".main")y && x".vscode")y && x"global")y && x"main.c")y && x"main.exe")y && x"README.md")y && x"tempCodeRunnerFile.c")y ) {
                if (entry->d_type == DT_DIR && strcmp (entry->d_name , argv[2]) == 0) {
                    DIR *dir1 = opendir (".main/master/stages");
                    chdir (".main/master/stages");
                    struct dirent *entry1;
                    entry1 = readdir (dir1);
                    do {
                        if (entry1->d_type == DT_DIR && strcmp (entry1->d_name , argv[2]) == 0) {
                            DIR *dir2 = opendir (argv[2]);
                            chdir (argv[2]);
                            struct dirent *entry2;
                            while ((entry2 = readdir (dir2)) != NULL) {
                                remove (entry2->d_name);
                            }
                            chdir ("..");
                            rmdir (argv[2]);
                        }
                    } while ((entry1 = readdir (dir1)) != NULL);
                    CreateDirectory (argv[2] , NULL);
                    chdir (argv[2]);
                    getcwd (fileaddress , sizeof (fileaddress));
                    chdir ("..");
                    chdir ("..");
                    chdir ("..");
                    chdir ("..");
                    DIR *dir11 = opendir (argv[2]);
                    chdir (argv[2]);
                    getcwd (fileinput , sizeof (fileinput));
                    struct dirent *entry11;
                    while ((entry11 = readdir (dir11)) != NULL) {
                        FILE *file1 = fopen (entry11->d_name , "r");
                        if (file1 != NULL) {
                            chdir (fileaddress);
                            char name_temp[100];
                            FILE *file2 = fopen (entry11->d_name , "w");
                            char ch = getc (file1);
                            while (ch != EOF) {
                                putc (ch , file2);
                                ch = getc (file1);
                            }
                            chdir (fileinput);
                            fclose (file2);
                        }
                        fclose (file1);
                    }
                    chdir ("..");
                } else if (strcmp (argv[2] , entry->d_name) == 0) {
                    FILE *file1 , *file2;
                    file1 = fopen (argv[2] , "r");
                    chdir (".main");
                    chdir ("master");
                    chdir ("stages");
                    file2 = fopen (argv[2] , "w");
                    char ch = getc (file1);
                    while (ch != EOF) {
                        putc (ch , file2);
                        ch = getc (file1);
                    }
                    fclose (file1);
                    fclose (file2);
                    for (int i = 0; i < 3; i++) {
                        chdir ("..");
                    }
                }
            }
        }
        int flag = 0;
        DIR *dir13 = opendir (".");
        struct dirent *entry13;
        while ((entry13 = readdir (dir13)) != NULL) {
            if (strcmp (argv[2] , entry13->d_name) == 0) {
                flag = 1;
            }
        }
        if (flag == 0) {
            printf ("%s is not existed.\n", argv[2]);
        }
    return 0;
}
int add_check (int argc , char *const argv[]) {
    char cwd[FILENAME_MAX];
    if (getcwd (cwd , sizeof(cwd)) == NULL) {
        return 1;
    }
    char temp_dir[FILENAME_MAX];
    bool exist = false;
    struct dirent *entry;
    do {
        DIR *dir = opendir (cwd);
        while ((entry = readdir (dir)) != NULL) {
            if (entry->d_type == DT_DIR && strcmp (entry->d_name , ".main") == 0) {
                exist = true;
            }
        }
        if (getcwd (temp_dir , sizeof(temp_dir)) == NULL) {
            return 1;
        }
        if (strcmp (temp_dir , "D:\\") != 0) {
            if (chdir ("..") != 0) {
                return 1;
            }
        }
    } while (strcmp (temp_dir , "D:\\") != 0);
    if (chdir (cwd) != 0) {
        return 1;
    }
    if (exist) {
        if (strcmp (argv[2] , "-f") == 0) { 
            addtostagef (argc , argv);
        } else {
            addtostage (argc , argv);
        }
    } else {
        perror ("there is no repository!\n");
    }
    return 0;
}
int resetf (int argc , char *const argv[]) {
    for (int p = 3; p < argc; p++) {
        if (chdir (".main") != 0) {
            perror ("there is no repository\n");
            return 1;
        }
        chdir ("master");
        DIR *dir = opendir ("stages");
        chdir ("stages");
        struct dirent *entry;
        int flag = 0;
        while ((entry = readdir(dir)) != NULL) {
            if (strcmp (entry->d_name , argv[p]) == 0 && entry->d_type == DT_DIR) {
                flag = 1;
                DIR *dir1 = opendir (argv[p]);
                chdir (argv[p]);
                struct dirent *entry1;
                while ((entry1 = readdir (dir1)) != NULL) {
                    remove (entry1->d_name);
                }
                if (chdir ("..") != 0) {
                    return 1;
                }    
                rmdir (argv[p]);        
            } else if (strcmp (entry->d_name , argv[p]) == 0) {
                flag = 1;
                remove (argv[p]);
            }
        }
        for (int i = 0; i < 3; i++) {
            chdir ("..");
        }
        if (flag == 0) {
            printf ("%s is not in stage.\n", argv[p]);
        }
    }
    return 0;
}
int reset (int argc , char *const argv[]) {
    if (chdir (".main") != 0) {
        perror ("there is no repository\n");
        return 1;
    }
    chdir ("master");
    DIR *dir = opendir ("stages");
    chdir ("stages");
    struct dirent *entry;
    int flag = 0;
    while ((entry = readdir(dir)) != NULL) {
        if (strcmp (entry->d_name , argv[2]) == 0 && entry->d_type == DT_DIR) {
            flag = 1;
            DIR *dir1 = opendir (argv[2]);
            chdir (argv[2]);
            struct dirent *entry1;
            while ((entry1 = readdir (dir1)) != NULL) {
                remove (entry1->d_name);
            }
            if (chdir ("..") != 0) {
                return 1;
            } 
            rmdir (argv[2]);        
        } else if (strcmp (entry->d_name , argv[2]) == 0) {
            flag = 1;
            remove (argv[2]);
        }
    }
    for (int i = 0; i < 3; i++) {
        chdir ("..");
    }
    if (flag == 0) {
        printf ("%s is not in stage.\n", argv[2]);
    }
    return 0;
}
int check_commit3 (char filename[] , char name[] , char FIRST_ADDRESS[]) {
    chdir (".main");
    chdir ("master");
    chdir ("commits");
    FILE *file = fopen ("commitnumber" , "r");
    int number = 0;
    if (file != NULL) {
        fscanf (file , "%d" , &number);
    }
    fclose (file);
    char combined[FILENAME_MAX];
    sprintf (combined , "commit%d" , number);
    if (number != 0) {
        chdir (combined);
        chdir (name);
        DIR *dir = opendir (".");
        struct dirent *entry;
        while ((entry = readdir (dir)) != NULL) {
            
            if (strcmp (filename , entry->d_name) == 0) {
                printf ("%s/%s :          -M\n", name , filename);
            }
        }
    } else {
        printf ("%s/%s :          -A\n" , name , filename);
    }
    chdir (FIRST_ADDRESS);
    return 0;
}
int check_commit2 (char name[] , char FIRST_ADDRESS[] , int type) {
    char address[FILENAME_MAX];
    getcwd (address , sizeof (address));
    chdir (name);
    char add1[FILENAME_MAX];
    getcwd (add1 , sizeof (add1));
    chdir (FIRST_ADDRESS);
    chdir (name);
    char add2[FILENAME_MAX];
    getcwd (add2 , sizeof (add2));
    DIR *dir2 = opendir (".");
    struct dirent *entry2;
    while ((entry2 = readdir (dir2)) != NULL) {
        chdir (add1);
        DIR *dir4 = opendir (".");
        struct dirent *entry4;
        int flag = 0;
        if (strcmp (entry2->d_name , ".") != 0 && strcmp (entry2->d_name , "..") != 0) {
            while ((entry4 = readdir (dir4)) != NULL) {
                if (strcmp (entry4->d_name , entry2->d_name) == 0) {
                    flag = 1;
                    printf ("%s/%s :          -M\n", name , entry4->d_name);
                }
            }
            if (flag == 0) {
                printf ("%s/%s :          -A\n", name , entry2->d_name);
            }
        }
    }
    chdir(add1);
    DIR *dir1 = opendir (".");
    struct dirent *entry1;
    while ((entry1 = readdir (dir1)) != NULL) {
        chdir (add2);
        DIR *dir3 = opendir (".");
        struct dirent *entry3;
        int flag = 0;
        if (strcmp (entry1->d_name , ".") != 0 && strcmp (entry1->d_name , "..") != 0) {
            while ((entry3 = readdir (dir3)) != NULL) {
                if (strcmp (entry1->d_name , entry3->d_name) == 0) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                printf ("%s/%s :          -D\n" , name , entry1->d_name);
            }
        }
    }
    chdir (address);
    return 0;
}
int check_commit1 (char name[] , char FIRST_ADDRESS[] , int type) {
    char address[FILENAME_MAX];
    getcwd (address , sizeof (address));
    chdir ("..");
    chdir ("commits");
    FILE *file = fopen ("commitnumber" , "r");
    int number = 0;
    if (file != NULL) {
        fscanf (file , "%d" , &number);
    }
    fclose (file);
    char combined[FILENAME_MAX];
    sprintf (combined , "commit%d" , number);
    if (number != 0) {
        chdir (combined);
        DIR *dir = opendir (".");
        struct dirent *entry;
        int flag = 0;
        while ((entry = readdir (dir)) != NULL) {
            if (strcmp (entry->d_name , name) == 0) {
                flag = 1;
                if (entry->d_type == DT_DIR) {
                    char temp[FILENAME_MAX];
                    getcwd (temp , sizeof (temp));
                    chdir (combined);
                    check_commit2 (name , FIRST_ADDRESS , type);
                    chdir (temp);
                } else {
                    printf ("%s :          -M\n", name);
                }
            }
        }
        if (flag == 0) {
            char temp[FILENAME_MAX];
            getcwd (temp , sizeof (temp));
            chdir (FIRST_ADDRESS);
            if (type == DT_DIR) {
                DIR *dir1 = opendir (name);
                struct dirent *entry1;
                while ((entry1 = readdir (dir1)) != NULL) {
                    if (strcmp (entry1->d_name , ".") != 0 && strcmp (entry1->d_name , "..") != 0) {
                        printf ("%s/%s :          -A\n" , name , entry1->d_name);
                    }
                }
            } else {
                printf ("%s :          -A\n" , name);
            }
            chdir (temp);
        }
    } else {
        if (type == DT_DIR) {
            char temp[FILENAME_MAX];
            getcwd (temp , sizeof (temp));
            chdir (FIRST_ADDRESS);
            chdir (name);
            DIR *dir24 = opendir (".");
            struct dirent *entry24;
            while ((entry24 = readdir (dir24)) != NULL) {
                if (strcmp (entry24->d_name , ".") != 0 && strcmp (entry24->d_name , "..") != 0) {
                    printf ("%s/%s :          -A\n" , name , entry24->d_name);
                }
            }
            chdir (temp);
        } else {
            printf ("%s :          -A\n" , name);
        }
    }
    chdir (address);
    return 0;
}
int check_stage (char name[] , char FIRST_ADDRESS[] , int type) {
    if (chdir (".main") != 0){
        perror ("there is no repository\n");
        return 1;
    }
    chdir ("master");
    DIR *dir = opendir ("stages");
    chdir ("stages");
    struct dirent *entry;
    int flag = 0;
    while ((entry = readdir (dir)) != NULL) {
        if (strcmp (entry->d_name , name) == 0) {
            flag = 1;
            if (entry->d_type == DT_DIR) {
                char address[FILENAME_MAX];
                getcwd (address , sizeof (address));
                chdir (name);
                char add1[FILENAME_MAX];
                getcwd (add1 , sizeof (add1));
                chdir (FIRST_ADDRESS);
                chdir (name);
                char add2[FILENAME_MAX];
                getcwd (add2 , sizeof (add2));
                DIR *dir1 = opendir (".");
                struct dirent *entry1;
                while ((entry1 = readdir (dir1)) != NULL) {
                    chdir (add1);
                    DIR *dir2 = opendir (".");
                    struct dirent *entry2;
                    int flag = 0;
                    if (strcmp (entry1->d_name , ".") != 0 && strcmp (entry1->d_name , "..") != 0) {
                        while ((entry2 = readdir (dir2)) != NULL) {
                            if (strcmp (entry1->d_name , entry2->d_name) == 0) {
                                flag = 1;
                                printf ("%s/%s :          +0\n" , name , entry1->d_name);
                            }
                        }
                        if (flag == 0) {
                            char temp[FILENAME_MAX];
                            getcwd (temp , sizeof (temp));
                            chdir (FIRST_ADDRESS);
                            check_commit3 (entry1->d_name , name , FIRST_ADDRESS);
                            chdir (temp);
                        }
                    }
                }
                chdir (address);
            } else {
                printf ("%s :          +0\n", entry->d_name);
            }
        }
    }
    if (flag == 0) {
        check_commit1 (name , FIRST_ADDRESS , type);
    }
    chdir (FIRST_ADDRESS);
    return 0;
}
int status (int argc , char *const argv[]) {
    char FIRST_ADDRESS[FILENAME_MAX];
    getcwd (FIRST_ADDRESS , sizeof (FIRST_ADDRESS));
    DIR *dir = opendir (".");
    struct dirent *entry;
    while ((entry = readdir (dir)) != NULL) {
        if (x".")y && x"..")y && x".git")y && x".main")y && x".vscode")y && x"global")y && x"main.c")y && x"main.exe")y && x"README.md")y && x"tempCodeRunnerFile.c")y ) {
            check_stage (entry->d_name , FIRST_ADDRESS , entry->d_type);
        }
    
    }
}
int make_folders_branch (char *const argv[] , char FIRST_ADDRESS[]) {
    CreateDirectory ("stages" , NULL);
    CreateDirectory ("commits" , NULL);
    char temp[FILENAME_MAX];
    getcwd (temp , sizeof (temp));
    chdir (FIRST_ADDRESS);
    DIR *dir = opendir (".");
    struct dirent *entry;
    while ((entry = readdir (dir)) != NULL) {
        if (x".")y && x"..")y && x".git")y && x".main")y && x".vscode")y && x"global")y && x"main.c")y && x"main.exe")y && x"README.md")y && x"tempCodeRunnerFile.c")y ) {
            if (entry->d_type == DT_DIR) {
                chdir (entry->d_name);
                char temp1[FILENAME_MAX];
                getcwd (temp1 , sizeof (temp1));
                chdir (temp);
                CreateDirectory (entry->d_name , NULL);
                chdir (entry->d_name);
                char temp2[FILENAME_MAX];
                getcwd (temp2 , sizeof (temp2));
                chdir (temp1);
                DIR *dir13 = opendir (".");
                struct dirent *entry13;
                while ((entry13 = readdir (dir13)) != NULL) {
                    if (strcmp (entry13->d_name , ".") != 0 && strcmp (entry13->d_name , "..") != 0) {
                        FILE *file11 = fopen (entry13->d_name , "r");
                        chdir (temp2);
                        FILE *file12 = fopen (entry13->d_name , "w");
                        char ch = getc (file11);
                        while (ch != EOF) {
                            putc (ch , file12);
                            ch = getc (file11);
                        }
                        fclose (file11);
                        fclose (file12);
                        chdir (temp1);
                    }
                }
                chdir (FIRST_ADDRESS);
            } else {
                FILE *file1 = fopen (entry->d_name , "r");
                chdir (temp);
                FILE *file2 = fopen (entry->d_name , "w");
                char ch = getc (file1);
                while (ch != EOF) {
                    putc (ch , file2);
                    ch = getc (file1);
                }
                fclose (file1);
                fclose (file2);
                chdir (FIRST_ADDRESS);
            }
        }
    }

    return 0;
}
int make_branch (int argc , char *const argv[]) {
    char FIRST_ADDRESS[FILENAME_MAX];
    getcwd (FIRST_ADDRESS , sizeof (FIRST_ADDRESS));
    chdir (".main");
    CreateDirectory (argv[2] , NULL);
    chdir (argv[2]);
    make_folders_branch (argv , FIRST_ADDRESS);
    chdir (FIRST_ADDRESS);
    return 0;
}
int commit (int argc , char *const argv[]) {
    if (argc > 4) {
        perror ("please enter the commit message between \"\"\n");
        return 1;
    }
    if (argc == 3) {
        perror ("please enter a commit message\n");
        return 1;
    }
    if (strlen (argv[3]) > 72) {
        perror ("the commit message is too long!\n");
        return 1;
    }
    char FIRST_ADDRESS[FILENAME_MAX];
    getcwd (FIRST_ADDRESS , sizeof (FIRST_ADDRESS));
    if (chdir (".main") != 0) {
        perror ("there is no repository!\n");
    }
    chdir ("master");
    chdir ("stages");
    char add_stage[FILENAME_MAX] , add_commit[FILENAME_MAX];
    getcwd (add_stage , sizeof(add_stage));
    chdir ("..");
    chdir ("commits");
    FILE *commitnumber = fopen ("commitnumber" , "r");
    int number = 0;
    if (commitnumber != NULL) {
        fscanf (commitnumber , "%d", &number);
    }
    fclose (commitnumber);
    number++;
    char combined[100];
    sprintf (combined , "commit%d", number);
    CreateDirectory (combined , NULL);
    commitnumber = fopen ("commitnumber" , "w");
    fprintf (commitnumber , "%d", number);
    fclose (commitnumber);
    chdir (combined);
    FILE *information = fopen ("information.txt" , "w");
    fprintf (information , "ID : %s\n" , combined);
    fprintf (information , "message : %s\n" , argv[3]);
    time_t t;
    time (&t);
    fprintf (information , "time : %s\n" , ctime (&t));
    fclose (information);
    getcwd (add_commit , sizeof (add_commit));
    DIR *dir = opendir (add_stage);
    struct dirent *entry;
    while ((entry = readdir (dir)) != NULL) {
        if (strcmp (entry->d_name , ".") != 0 && strcmp (entry->d_name , "..") != 0){
            if (entry->d_type == DT_DIR) {
                FILE *file1 , *file2;
                char add1[FILENAME_MAX] , add2[FILENAME_MAX];
                chdir (add_stage);
                chdir (entry->d_name);
                getcwd (add1 , sizeof (add1));
                chdir (add_commit);
                CreateDirectory (entry->d_name , NULL);
                chdir (entry->d_name);
                getcwd (add2 , sizeof (add2));
                chdir (add1);
                DIR *dir1 = opendir (".");
                struct dirent *entry1;
                while ((entry1 = readdir (dir1)) != NULL) {
                    if (strcmp (entry1->d_name , ".") != 0 && strcmp (entry1->d_name , "..") != 0) {
                        char temp[FILENAME_MAX];
                        getcwd (temp , sizeof(temp));
                        file1 = fopen (entry1->d_name , "r");
                        chdir (add2);
                        file2 = fopen (entry1->d_name , "w");
                        char ch = getc (file1);
                        while (ch != EOF) {
                            putc (ch , file2);
                            ch = getc (file1);
                        }   
                        fclose (file1);
                        fclose (file2);
                    }
                    chdir (add1);
                    remove (entry1->d_name);
                    char temp[FILENAME_MAX];
                    getcwd (temp , sizeof (temp));
                }
                chdir ("..");
                rmdir (entry->d_name);
                chdir (add_commit);
            } else {
                FILE *file1 , *file2;
                chdir (add_stage);
                file1 = fopen (entry->d_name , "r");
                chdir (add_commit);
                file2 = fopen (entry->d_name , "w");
                char ch = getc (file1);
                while (ch != EOF) {
                    putc (ch , file2);
                    ch = getc (file1); 
                }
                fclose (file1);
                fclose (file2);
                chdir (add_stage);
                remove (entry->d_name);
                chdir (add_commit);
                chdir ("..");
            }
        }
    }
    chdir (FIRST_ADDRESS);
    return 0;
}
int normal_log (int argc , char *const argv[]) {
    char FIRST_ADDRESS[FILENAME_MAX];
    getcwd (FIRST_ADDRESS , sizeof (FIRST_ADDRESS));
    chdir (".main");
    DIR *dir = opendir (".");
    struct dirent *entry;
    while ((entry = readdir (dir)) != NULL) {
        char temp1[FILENAME_MAX];
        getcwd (temp1 , sizeof (temp1));
        if (strcmp (entry->d_name , ".") != 0 && strcmp (entry->d_name , "..") != 0 && strcmp (entry->d_name , "local") != 0) {
            chdir (entry->d_name);
            chdir ("commits");
            DIR * dir12 = opendir (".");
            struct dirent *entry12;
            while ((entry12 = readdir (dir12)) != NULL) {
                char temp2[FILENAME_MAX];
                getcwd (temp2 , sizeof (temp2));
                if (strcmp (entry12->d_name , ".") != 0 && strcmp (entry12->d_name , "..") != 0 && strcmp (entry12->d_name , "commitnumber") != 0) {
                    printf ("--------------------------------------\n");
                    printf ("Branch : %s\n" , entry->d_name);
                    chdir (entry12->d_name);
                    FILE *ggg = fopen ("information.txt" , "r");
                    char ch = getc (ggg);
                    while (ch != EOF) {
                        printf ("%c" , ch);
                        ch = getc (ggg);
                    }
                    fclose (ggg);
                }
                chdir (temp2);
            }
        }
        chdir (temp1);
    }
    chdir (FIRST_ADDRESS);
    return 0;
}
int log_branch (int argc , char *const argv[]) {
    char FIRST_ADDRESS[FILENAME_MAX];
    getcwd (FIRST_ADDRESS , sizeof (FIRST_ADDRESS));
    if (chdir (".main") != 0) {
        perror ("there is no repository\n");
        return 1;
    }
    DIR *dir = opendir (".");
    struct dirent *entry;
    int flag = 0;
    while ((entry = readdir (dir)) != NULL) {
        char temp1[FILENAME_MAX];
        getcwd (temp1 , sizeof (temp1));
        if (strcmp (entry->d_name , argv[3]) == 0) {
            flag = 1;
            chdir (argv[3]);
            chdir ("commits");
            DIR *dir13 = opendir (".");
            struct dirent *entry13;
            while ((entry13 = readdir (dir13)) != NULL) {
                char temp2[FILENAME_MAX];
                getcwd (temp2 , sizeof (temp2));
                if (strcmp (entry13->d_name , ".") != 0 && strcmp (entry13->d_name , "..") != 0 && strcmp (entry13->d_name , "commitnumber.txt") != 0) {
                    printf ("----------------------------------------\n");
                    chdir (entry13->d_name);
                    FILE *ggg = fopen ("information.txt" , "r");
                    char ch = getc (ggg);
                    while (ch != EOF) {
                        printf ("%c" , ch);
                        ch = getc (ggg);
                    }
                    fclose (ggg);
                }
                chdir (temp2);
            }
        }
        chdir (temp1);
    }
    if (flag == 0) {
        printf ("%s is not a branch\n" , argv[3]);
        chdir (FIRST_ADDRESS);
        return 1;
    }


    chdir (FIRST_ADDRESS);
    return 0;
}
int log_number (int argc , char *const argv[]) {
    char FIRST_ADDRESS[FILENAME_MAX];
    getcwd (FIRST_ADDRESS , sizeof (FIRST_ADDRESS));
    if (chdir (".main") != 0) {
        perror ("there is no repository\n");
        return 1;
    }
    chdir ("master/commits");
    FILE *file = fopen ("commitnumber" , "r");
    int number;
    if (file != NULL) {
        int z;
        fscanf (file , "%d" , &number);
        sscanf (argv[3] , "%d" , &z);
        for (int i = number; i > number - z; i--) {
            char combined[FILENAME_MAX];
            sprintf (combined , "commit%d" , i);
            chdir (FIRST_ADDRESS);
            chdir (".main");
            DIR *dir = opendir (".");
            struct dirent *entry;
            while ((entry = readdir (dir)) != NULL) {
                char temp1[FILENAME_MAX];
                getcwd (temp1 , sizeof (temp1));
                if (strcmp (entry->d_name , ".") != 0 && strcmp (entry->d_name , "..") != 0 && strcmp (entry->d_name , "local") != 0) {
                    chdir (entry->d_name);
                    chdir ("commits");
                    DIR *dir1 = opendir (".");
                    struct dirent *entry1;
                    while ((entry1 = readdir (dir1)) != NULL) {
                        if (strcmp (combined , entry1->d_name) == 0) {
                            printf ("----------------------------------------\n");
                            printf ("%s :\n" , combined);
                            chdir (entry1->d_name);
                            FILE *information = fopen ("information.txt" , "r");
                            char ch = getc (information);
                            while (ch != EOF) {
                                printf ("%c" , ch);
                                ch = getc (information);
                            }
                            fclose (information);
                        }
                    }
                }
                chdir (temp1);
            }
        }
    } else {
        perror ("there is no commit\n");
        return 1;
    }
    fclose (file);
    chdir (FIRST_ADDRESS);
    return 0;
}
int main (int argc , char *argv[]) {
    if (argc < 2) {
        perror ("please enter a valid command:\n");
        return 1;
    }
    if (strcmp (argv[1] , "config") == 0) {
        if (argc == 2) {
            perror ("invalid command!\n");
            return 1;
        } else if (strcmp (argv[2] , "-global") == 0) {
            if (argc == 3) {
                perror ("invalid command!\n");
                return 1;
            } else if (argc > 4) {
                perror ("invalid command\n");
            } else {
                global (argc , argv);
            }
        } else {
                local (argc , argv);
        }
    } else if (strcmp (argv[1] , "init") == 0) {
        init (argc , argv);
    } else if (strcmp (argv[1] , "add") == 0) {
        if (argc == 2) {
            perror ("please enter a file or a directory.\n");
            return 1;
        }
        add_check (argc , argv);
            
            
            
             
    } else if (strcmp (argv[1] , "reset") == 0) {
        if (argc == 2) {
            perror ("please enter a file or a directory.\n");
            return 1;
        }
        if (strcmp (argv[2] , "-f") == 0) {
            if (argc == 3) {
                perror ("please enter a file or a directory.\n");
                return 1;
            }
            resetf (argc , argv);
        } else {
            reset (argc , argv);
        }
    } else if (strcmp (argv[1] , "status") == 0) {
        if (argc > 2) {
            perror ("invalid command\n");
            return 1;
        }
        status (argc , argv);
    } else if (strcmp (argv[1] , "commit") == 0) {
        if (strcmp (argv[2] , "-m") == 0) {
            commit (argc , argv);
        } else {
            perror ("invalid command!\n");
            return 1;
        }
    } else if (strcmp (argv[1] , "branch") == 0) {
        if (argc == 2) {
            chdir (".main");
            DIR *dir = opendir (".");
            struct dirent *entry;
            while ((entry = readdir (dir)) != NULL) {
                if (strcmp (entry->d_name , "local") != 0 && strcmp (entry->d_name , ".") != 0 && strcmp (entry->d_name , "..") != 0) {
                    printf ("%s\n" , entry->d_name);
                }
            }
            chdir ("..");
        } else {
            make_branch (argc , argv);
        }
    } else if (strcmp (argv[1] , "log") == 0) {
        if (argc == 2) {
            normal_log (argc , argv);
        } else if (argc == 4) {
            if (strcmp (argv[2] , "-n") == 0) {
                log_number (argc , argv);
            } else if (strcmp (argv[2] , "-branch") == 0) {
                log_branch (argc , argv);
            } else if (strcmp (argv[2] , "-author") == 0) {
            } else if (strcmp (argv[2] , "-since") == 0) {
            } else if (strcmp (argv[2] , "-before") == 0) {
            } else if (strcmp (argv[2] , "-search") == 0) {
            } else {
                perror ("please enter a valid command\n");
                return 1;
            }
        } else {
            perror ("please enter a valid command\n");
            return 1;
        }
    } else {
        perror ("this is not a valid command\n");
    }
    return 0;
}