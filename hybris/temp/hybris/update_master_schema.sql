CREATE CACHED TABLE datahubinstances
(
    hjmpTS BIGINT,
    createdTS TIMESTAMP,
    modifiedTS TIMESTAMP,
    TypePkString BIGINT,
    OwnerPkString BIGINT,
    PK BIGINT NOT NULL,
    p_instancename NVARCHAR(255),
    p_instancelocation NVARCHAR(255),
    aCLTS BIGINT DEFAULT 0,
    propTS BIGINT DEFAULT 0,
    PRIMARY KEY (PK)
);

