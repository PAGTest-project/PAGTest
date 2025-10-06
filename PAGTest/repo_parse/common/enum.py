

from enum import Enum


class LanguageEnum(Enum):
    PYTHON = "python"
    JAVA = "java"
    CPP = "cpp"
    GO = "go"

LANGUAGE_TO_SUFFIX = {
    LanguageEnum.PYTHON: "py",
    LanguageEnum.JAVA: "java",
    LanguageEnum.CPP: "cpp",
    LanguageEnum.GO: "go",
}