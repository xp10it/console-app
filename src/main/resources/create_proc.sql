create or replace procedure applyInflation()
as
$token$

begin
    update items
    set price = price * 1.1;
end;

$token$

language plpgsql;

create or replace function getExpensiveItems(lowestPrice real)
returns setof items
as

$token$

begin
    return query select * from items where price > lowestPrice;
end;

$token$

language plpgsql;