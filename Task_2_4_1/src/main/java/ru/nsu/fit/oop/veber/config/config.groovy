package ru.nsu.fit.oop.veber.config

import ru.nsu.fit.oop.veber.model.Group
import ru.nsu.fit.oop.veber.model.Student

group = new Group(
        number: 21214,
        students: [
                new Student(
                        nickname: "fagorym",
                        fullName: "Вебер Олег Владимирович",
                        repositoryUrl: "https://github.com/Fagorym/OOP"
                ),
                new Student(
                        nickname: "tretiakovvv",
                        fullName: "Третьяков Артем Александрович",
                        repositoryUrl: "https://github.com/Tretiakovv/oop"
                ),
                new Student(
                        nickname: "mihailCCfit",
                        fullName: "Цуканов Михаил Сергеевич",
                        repositoryUrl: "https://github.com/MihailCCfit/oop"
                )
        ]
)
return group