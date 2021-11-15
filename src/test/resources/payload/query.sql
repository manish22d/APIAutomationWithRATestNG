SELECT DISTINCT
       MEME.MEME_CK,
       MEME.SBSB_CK,
       MEPE.GRGR_CK,
       rtrim (CSPI_ITS_PREFIX)
          ITSPREFIX,
       rtrim (SBSB.SBSB_ID)
          SUBSCRIBERID,
       concat ('0', MEME_SFX)
          DEPENDENTCODE,
       concat (SBSB_ID, '0', MEME_SFX)
          UNIQUEMEMBERID,
       rtrim (PAGR.PAGR_ID)
          PARENTGROUPID,
       rtrim (PAGR.PAGR_NAME)
          PARENTGROUPNAME,
       rtrim (GRGR.GRGR_ID)
          GROUPID,
       rtrim (GRGR.GRGR_NAME)
          GROUPNAME,
       rtrim (SGSG.SGSG_ID)
          SUBGROUPID,
       rtrim (SGSG.SGSG_NAME)
          SUBGROUPNAME,
       rtrim (MEME_LAST_NAME)
          MEMBERLASTNAME,
       rtrim (MEME_FIRST_NAME)
          MEMBERFIRSTNAME,
       rtrim (MEME_MID_INIT)
          MEMBERMIDINITIAL,
       rtrim (MEME_TITLE)
          SUFFIX,
       format (MEME_BIRTH_DT, 'yyyy-MM-dd')
          DOB,
       rtrim (MEME_SEX)
          GENDER,
       rtrim (MEME_REL)
          RELATIONSHIP,
       rtrim (MEME_MARITAL_STATUS)
          MARITAL_STATUS,
       RTRIM (LANG.MCTR_DESC)
          Language,
       CASE
          WHEN NULLIF (RTRIM (RACE.MCTR_DESC), '') IS NULL
          THEN
             'Not Assigned'
          ELSE
             RTRIM (RACE.MCTR_DESC)
       END
          AS Race,
       CASE
          WHEN NULLIF (RTRIM (ETHN.MCTR_DESC), '') IS NULL
          THEN
             'Not Assigned'
          ELSE
             RTRIM (ETHN.MCTR_DESC)
       END
          AS Ethnicity,
       rtrim (SBAD_HOME.SBAD_ADDR1)
          HOME_STREET_ADDRESS_1,
       rtrim (SBAD_HOME.SBAD_ADDR2)
          HOME_STREET_ADDRESS_2,
       rtrim (SBAD_HOME.SBAD_ADDR3)
          HOME_STREET_ADDRESS_3,
       rtrim (SBAD_HOME.SBAD_CITY)
          HOME_CITY,
       rtrim (SBAD_HOME.SBAD_COUNTY)
          HOME_COUNTY,
       rtrim (SBAD_HOME.SBAD_STATE)
          HOME_STATE,
       rtrim (SBAD_HOME.SBAD_CTRY_CD)
          HOME_COUNTRY,
       rtrim (SUBSTRING (SBAD_HOME.SBAD_ZIP, 1, 5))
          HOME_ZIP,
       rtrim (SUBSTRING (SBAD_HOME.SBAD_ZIP, 6, 9))
          HOME_ZIP_EXT,
       rtrim (SBAD_HOME.SBAD_PHONE)
          HOME_PHONE,
       rtrim (SBAD_HOME.SBAD_EMAIL)
          HOME_EMAIL,
       rtrim (SBAD_MAIL.SBAD_ADDR1)
          MAILADDRESSLINE1,
       rtrim (SBAD_MAIL.SBAD_ADDR2)
          MAILADDRESSLINE2,
       rtrim (SBAD_MAIL.SBAD_ADDR3)
          MAILADDRESSLINE3,
       rtrim (SBAD_MAIL.SBAD_CITY)
          MAILCITY,
       rtrim (SBAD_MAIL.SBAD_COUNTY)
          MAILCOUNTY,
       rtrim (SBAD_MAIL.SBAD_STATE)
          MAILSTATE,
       rtrim (SBAD_MAIL.SBAD_CTRY_CD)
          MAILCOUNTRY,
       rtrim (SUBSTRING (SBAD_MAIL.SBAD_ZIP, 1, 5))
          MAILZIP,
       rtrim (SUBSTRING (SBAD_MAIL.SBAD_ZIP, 6, 9))
          MAILZIPSUFFIX,
       rtrim (SBAD_MAIL.SBAD_PHONE)
          MAILPHONE,
       rtrim (SBAD_MAIL.SBAD_EMAIL)
          MAILEMAIL,
       -- DNC INDICATOR
       rtrim (CICI_NAME)
          INSURANCECARRIER,
       --EDE_DATA_ELEM_VAL_DESC InsuranceCategory, Lookup table
       rtrim (PDDS_PROD_TYPE)
          INSURANCEPLANNETWORKTYPE,
       rtrim (PLDS_DESC)
          INSURANCEPLANIDENTIFIER,
       --BGBG_ID CMSContract, Lookup table
       rtrim (NWST_PFX)
          NETWORKSET,
       --PRGRM_NM ProgramCatagory, -- membership table
       --case when MECB_INSUR_ORDER = 'P'
       --and MECB_INSUR_TYPE in ('C','M','O')
       --Then
       --         'N'
       --Else 'Y' end as CambiaPrimaryFlag,
       FORMAT (MEPE.MEPE_EFF_DT, 'yyyy-MM-dd')
          ELIGIBLEFROMDT,
       FORMAT (MEPE.MEPE_TERM_DT, 'yyyy-MM-dd')
          ELIGIBLETHRUDT,
       rtrim (MEME_CELL_PHONE)
          MEMBERCELLPHONE,
       rtrim (PDPD.PDPD_ID)
          PRODUCTID,
       rtrim (MEPE.EXCD_ID)
          EXPLAINATIONCODE,
       rtrim (EXCD_SHORT_TEXT)
          EXPLAINATIONTEXT,
       rtrim (LOBD_ID)
          LINEOFBUSINESS,
       --CASE
       --  WHEN PDDS.PDDS_PREM_IND in ('P','A','M') then 'ASO'
       --  WHEN GRGR.GRGR_MCTR_TYPE in ('MDCG', 'MDCH', 'MDCR', 'MDGP') then 'Medicare'
       --  ELSE 'Commercial'
       --END as DerivedLOB,
       rtrim (MEME_SSN)
          MEMBERSSN,
       rtrim (NWST_PFX)
          HASDISCOUNTNETWORK,
       rtrim (PCP.PRPR_ID)
          PCP_ID,
       rtrim (PCP.PRPR_NPI)
          PCP_NPI,
       rtrim (PCP.PRPR_NAME)
          PCP_Name,
       rtrim (MCTN_ID)
          PCP_Tin,
       PDDS_PREM_IND,
       CASE WHEN PDDS_PREM_IND IN ('P', 'A', 'M') THEN 'Y' ELSE 'N' END
          AS ASO_IND,
       rtrim (PDDS_DESC)
          PRODUCTDESCRIPTION,
       rtrim (MEPE.MEPE_CREATE_DTM)
          LASTUPDATEDDATE,
       rtrim (MEPE.CSPD_CAT)
          CLASSPROGRAMCATEGORY
FROM facppm1..CMC_MEPE_PRCS_ELIG MEPE
     INNER JOIN facppm1..CMC_MEME_MEMBER MEME
        ON     MEPE.MEME_CK = MEME.MEME_CK
           AND MEPE.MEPE_ELIG_IND = 'Y'
           AND MEPE.CSPD_CAT IN ('M', 'R')
           AND MEPE.MEPE_TERM_DT >= GETDATE () -- PASS THIS AS A PARM START _DT
           AND MEPE.MEPE_EFF_DT <= GETDATE ()    -- Pass this as a Parm End_dt
           AND MEPE.MEPE_EFF_DT <> MEPE.MEPE_TERM_DT
     LEFT JOIN facppm1..CMC_MEPE_PRCS_ELIG CURR_MEPE
        ON     MEPE.MEME_CK = CURR_MEPE.MEME_CK
           AND CURR_MEPE.CSPD_CAT IN ('M', 'R')
           AND GETDATE () BETWEEN CURR_MEPE.MEPE_EFF_DT
                              AND CURR_MEPE.MEPE_TERM_DT
     INNER JOIN facppm1..CMC_GRGR_GROUP GRGR ON MEPE.GRGR_CK = GRGR.GRGR_CK
     INNER JOIN facppm1..CMC_SBSB_SUBSC SBSB ON MEME.SBSB_CK = SBSB.SBSB_CK
     INNER JOIN facppm1..CMC_PAGR_PARENT_GR PAGR
        ON GRGR.PAGR_CK = PAGR.PAGR_CK
     INNER JOIN facppm1..CMC_CSPI_CS_PLAN CSPI
        ON     MEPE.CSPI_ID = CSPI.CSPI_ID
           AND MEPE.CSCS_ID = CSPI.CSCS_ID
           AND GRGR.GRGR_CK = CSPI.GRGR_CK
           --   AND MEPE.CSPD_CAT = CSPI.CSPD_CAT
           AND MEPE.MEPE_EFF_DT BETWEEN CSPI.CSPI_EFF_DT
                                    AND CSPI.CSPI_TERM_DT
     LEFT JOIN facppm1..CMC_PDPD_PRODUCT PDPD ON MEPE.PDPD_ID = PDPD.PDPD_ID
     LEFT JOIN facppm1..CMC_PLDS_PLAN_DESC PLDS
        ON CSPI.CSCS_ID = PLDS.CSPI_ID
     LEFT JOIN facppm1..CMC_SBAD_ADDR SBAD_MAIL
        ON     MEME.SBSB_CK = SBAD_MAIL.SBSB_CK
           AND MEME.SBAD_TYPE_MAIL = SBAD_MAIL.SBAD_TYPE
     LEFT JOIN facppm1..CMC_SBAD_ADDR SBAD_HOME
        ON     MEME.SBSB_CK = SBAD_HOME.SBSB_CK
           AND MEME.SBAD_TYPE_HOME = SBAD_HOME.SBAD_TYPE
     LEFT JOIN facppm1..CMC_MECB_COB COB ON COB.MEME_CK = MEME.MEME_CK
     LEFT JOIN facppm1..CMC_EXCD_EXPL_CD EXCD ON EXCD.EXCD_ID = GRGR.EXCD_ID
     LEFT JOIN facppm1..CMC_PDDS_PROD_DESC PDDS
        ON PDPD.PDPD_ID = PDDS.PDPD_ID
     LEFT JOIN facppm1..CER_CICI_CLIENT_D CICI ON CICI.CICI_ID = GRGR.CICI_ID
     LEFT JOIN facppm1..CMC_SGSG_SUB_GROUP SGSG
        ON SGSG.GRGR_CK = GRGR.GRGR_CK AND MEPE.SGSG_CK = SGSG.SGSG_CK
     LEFT JOIN facppm1.dbo.CMC_MCTR_CD_TRANS LANG
        ON     LANG.MCTR_ENTITY = 'ALL'
           AND LANG.MCTR_TYPE = 'LANG'
           AND LANG.MCTR_VALUE = MEME.MEME_MCTR_LANG
     LEFT JOIN facppm1.dbo.CMC_MCTR_CD_TRANS RACE
        ON     RACE.MCTR_ENTITY = 'MEME'
           AND RACE.MCTR_TYPE = 'RACE'
           AND RACE.MCTR_VALUE = MEME.MEME_MCTR_RACE_NVL
     LEFT JOIN facppm1.dbo.CMC_MCTR_CD_TRANS ETHN
        ON     ETHN.MCTR_ENTITY = 'MEME'
           AND ETHN.MCTR_TYPE = 'ETHN'
           AND ETHN.MCTR_VALUE = MEME.MEME_MCTR_ETHN_NVL
     LEFT JOIN CMC_MEPR_PRIM_PROV MEPR
        ON MEME.MEME_CK = MEPR.MEME_CK AND MEPR.MEPR_PCP_TYPE = 'MP'
     LEFT JOIN CMC_PRPR_PROV PCP ON MEPR.PRPR_ID = PCP.PRPR_ID
     LEFT JOIN facppm1.dbo.CMC_MECB_COB MECB                     -- 06/03/2020
        ON     MEME.MEME_CK = MECB.MEME_CK
           AND getdate () <= MECB.MECB_TERM_DT
           AND getdate () >= MECB.MECB_EFF_DT
           AND MECB.MECB_EFF_DT <> MECB.MECB_TERM_DT
           AND MECB.MECB_INSUR_TYPE IN ('C', 'M', 'O')
           AND MECB.MECB_INSUR_ORDER = 'P