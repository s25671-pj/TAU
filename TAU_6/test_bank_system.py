import pytest
import pytest_asyncio
from unittest.mock import AsyncMock, patch
from bank_system import Account, Bank, InsufficientFundsError

def test_deposit():
    account = Account("1", "Mariusz Pudzianowski", 400.0)
    account.deposit(40.0)
    assert account.balance == 440.0

    with pytest.raises(ValueError):
        account.deposit(-10.0)

def test_withdraw():
    account = Account("1", "Mariusz Pudzianowski", 100.0)
    account.withdraw(10.0)
    assert account.balance == 90.0

    with pytest.raises(InsufficientFundsError):
        account.withdraw(200.0)

    with pytest.raises(ValueError):
        account.withdraw(-10.0)

@pytest_asyncio.fixture
async def accounts():
    account1 = Account("1", "Mariusz Pudzianowski", 100.0)
    account2 = Account("2", "Žydrūnas Savickas", 50.0)
    return account1, account2

@pytest.mark.asyncio
async def test_transfer(accounts):
    account1, account2 = accounts
    await account1.transfer(account2, 50.0)
    assert account1.balance == 50.0
    assert account2.balance == 100.0

    with pytest.raises(InsufficientFundsError):
        await account1.transfer(account2, 100.0)

    with pytest.raises(ValueError):
        await account1.transfer(account2, -20.0)

def test_create_account():
    bank = Bank()
    bank.create_account("1", "Mariusz Pudzianowski", 100.0)
    assert "1" in bank.accounts

    with pytest.raises(ValueError):
        bank.create_account("1", "Žydrūnas Savickas", 200.0)

def test_get_account():
    bank = Bank()
    bank.create_account("1", "Mariusz Pudzianowski", 100.0)
    account = bank.get_account("1")
    assert account.owner == "Mariusz Pudzianowski"

    with pytest.raises(ValueError):
        bank.get_account("999")

@pytest.mark.asyncio
async def test_process_transaction():
    bank = Bank()
    bank.create_account("1", "Mariusz Pudzianowski", 100.0)
    bank.create_account("2", "Žydrūnas Savickas", 50.0)
    
    async def transaction():
        from_acc = bank.get_account("1")
        to_acc = bank.get_account("2")
        await from_acc.transfer(to_acc, 50.0)

    await bank.process_transaction(transaction)
    assert bank.get_account("1").balance == 50.0
    assert bank.get_account("2").balance == 100.0

@pytest.mark.asyncio
async def test_transfer_with_mock():
    account1 = Account("1", "Mariusz Pudzianowski", 100.0)
    account2 = Account("2", "Žydrūnas Savickas", 50.0)

    with patch("asyncio.sleep", new_callable=AsyncMock):
        await account1.transfer(account2, 50.0)

    assert account1.balance == 50.0
    assert account2.balance == 100.0