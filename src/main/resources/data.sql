drop table if exists invoice;
drop table if exists line_item;

create table invoice
(
    id           bigint primary key,
    client       varchar(250) null,
    invoice_date timestamp    not null,
    vat_rate     bigint       null,
    subTotal     decimal      null,
    vat          decimal      null,
    total        decimal      null
);

create table line_item
(
    id          bigint primary key,
    quantity    bigint       not null,
    description varchar(250) null,
    unit_price  decimal      not null
);