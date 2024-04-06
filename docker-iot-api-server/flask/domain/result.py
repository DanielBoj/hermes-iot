import abc


class Result(metaclass=abc.ABCMeta):

    @abc.abstractmethod
    def is_error(self) -> bool:
        pass

    @abc.abstractmethod
    def is_success(self) -> bool:
        pass


class Data(Result):

    def __init__(self, data: any):
        self.data = data

    def is_error(self) -> bool:
        return False

    def is_success(self) -> bool:
        return True


class Error(Result):
    def __init__(self, error_message: str):
        self.error_message = error_message

    def is_error(self) -> bool:
        return True

    def is_success(self) -> bool:
        return False
