package kz.fintech.commons.feignclients;

import kz.fintech.models.LocalizedString;
import kz.fintech.models.refs.*;
import kz.fintech.models.refs.location.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@FeignClient(ServiceNames.NRI_SERVICE)
public interface NriServiceClient {

    /*@GetMapping("/menu/all")
    List<Menu> getAllMenuList();

    @GetMapping("/location/countries")
    List<Country> getCountries();

    @GetMapping("/location/country/{code}")
    Country getCountryByCode(@PathVariable("code") String code);

    @GetMapping("/location/address/find-streets")
    List<PostObject> findStreets(@RequestParam("kato[]") String[] katos, @RequestParam("street") String street);

    @GetMapping("/location/address/find-buildings")
    List<PostBuilding> findBuildings(@RequestParam("street-id") String streetId, @RequestParam("house") String houseNumber);

    @GetMapping("/location/address-types")
    List<Reference> allAddressTypes();

    @GetMapping("/location/address/hierarchy-from-leaf/{objectId}")
    LocalizedString getPostObjectHierarchy(@PathVariable("objectId") String objectId);

    @GetMapping("/location/address/building-by-zipcode/{zipCode}")
    PostBuilding getPostBuildingByZipCode(@PathVariable("zipCode") String zipCode);

    @GetMapping("/location/gov/{code}/kato")
    String findKatoByGovCode(@PathVariable("code") String code);

    @GetMapping("/location/gov/nationality/{id}")
    Nationality getNationality(@PathVariable("id") int id);

    @GetMapping("/location/gov/nationality")
    List<Nationality> getNationalities();

    @GetMapping("/location/gov/citizenship/{id}")
    Citizenship getCitizenship(@PathVariable("id") int id);

    @GetMapping("/location/gov/citizenships")
    List<Citizenship> getCitizenships();

    @GetMapping("/location/gov/country/{id}")
    GovCountry getGovCountry(@PathVariable("id") int id);

    @GetMapping("/location/gov/countries")
    List<GovCountry> getGovCountries();

    @GetMapping("/commons/id-authorities")
    List<IdentityDocumentAuthority> getIdentityDocumentAuthorities();

    @GetMapping("/commons/id-authorities-by-id/{id}")
    IdentityDocumentAuthority getIdentityDocumentAuthorityById(@PathVariable("id") int id);

    @GetMapping("/commons/customer-document-types")
    List<CustomerDocumentType> getCustomerDocumentTypes();

    @GetMapping("/commons/krps")
    List<Reference> findAllKrps();

    @GetMapping("/commons/kopfs")
    List<Reference> findAllKopfs();

    @GetMapping("/commons/okeds")
    List<Oked> findAllOkeds();

    @GetMapping("/commons/okeds/{okedName}")
    List<Oked> findOkeds(@PathVariable("okedName") String okedName);

    @GetMapping("/commons/oked/{okedCode}")
    Oked findOked(@PathVariable("okedCode") String okedCode);

    @GetMapping("/commons/okedmcc/{okedCode}")
    OkedMcc findOkedMcc(@PathVariable("okedCode") String okedCode);

    @GetMapping("/commons/banks-by-country/{countryCode}")
    List<Bank> findBanksByCountryCode(@PathVariable("countryCode") String countryCode);

    @GetMapping("/commons/social-statuses")
    List<Reference> allSocialStatuses();

    @GetMapping("/commons/income-sources")
    List<Reference> allIncomeSources();

    @GetMapping("/commons/marital-statuses")
    List<MaritalStatus> allMaritalStatuses();

    @GetMapping("/commons/customer-relationships")
    List<Reference> allCustomerRelationships();

    @GetMapping("/commons/asset-types")
    List<Reference> allAssetTypes();

    @GetMapping("/commons/property-types")
    List<Reference> allPropertyTypes();

    @GetMapping("/commons/education-types")
    List<Reference> allEducationTypes();

    @GetMapping("/commons/currencies")
    List<Currency> allCurrencies();

    @GetMapping("/org-unit/all")
    List<OrgUnit> getAllOrgUnitsAsTree();

    @GetMapping("/org-unit/all/branches")
    List<OrgUnit> getAllBranches();

    @RequestMapping(method = RequestMethod.GET, value = "/user/find-by-account-name/{accountName}")
    User findUserByAccountName(@PathVariable("accountName") String accountName);

    @GetMapping("/user/find-by-account-name-full/{accountName}")
    User findByAccountNameFull(@PathVariable("accountName") String accountName);

    @GetMapping("/user/find-by-email/{email}")
    List<User> findUsersByEmail(@PathVariable("email") String email);

    @GetMapping("/user/find-users-by-branch-and-role")
    List<User> findBranchUsersWithRole(@RequestParam("branchCode") String branchCode, @RequestParam("roleCode") String roleCode);

    @GetMapping("/user/get-users-by-orgunit-parentid")
    List<User> getUsersByOrgUnitParentId();

    @GetMapping("/org-unit/find-by-id/{orgUnitId}")
    OrgUnit getOrgUnitById(@PathVariable("orgUnitId") int orgUnitId);

    @GetMapping("/org-unit/find-by-code/{orgUnitCode}")
    OrgUnit getOrgUnitByCode(@PathVariable("orgUnitCode") String orgUnitCode);

    @GetMapping("/org-unit/find-by-parent-code/{parentCode}")
    List<OrgUnit> findOrgUnitsByParentCode(@PathVariable("parentCode") String parentCode);

    @GetMapping("/user/all")
    List<User> getAllUsers();

    @PostMapping("/user/save")
    void saveUser(@RequestBody User user);

    @PostMapping("/user/save-roles-for-user")
    void saveRolesForUser(@RequestParam("userId") int userId, @RequestParam("roleCode") String roleCode);

    @PostMapping("/user/save-substitutor")
    Boolean saveCurator(@RequestBody Substitutor substitutor);

    @GetMapping("/user/find-by-user-id/{userId}")
    User findUserById(@PathVariable("userId") int userId);

    @GetMapping("/user/find-by-name/{name}")
    List<User> findUserByName(@PathVariable("name") String name);

    @GetMapping("/user/find-user-ids-by-orgunit-id/{orgunitid}")
    List<Integer> findUserIdsByOrgUnitId(@PathVariable("orgunitid") int orgUnitId);

    @GetMapping("/user/find-curator-by-org-id/{orgUnitId}")
    Curator findCuratorByOrgUnitId(@PathVariable("orgUnitId") int orgUnitId);

    @GetMapping("/user/get-users-by-group-code/{groupCode}")
    List<User> getUsersByGroupCode(@PathVariable("groupCode") String groupCode);

    @GetMapping("/user/find-groups-by-user-account/{accountName}")
    List<Group> findGroupsByUserAccount(@PathVariable("accountName") String accountName);

    @GetMapping("/user/find-curator-by-code/{curatorCode}")
    Curator findGroupsCurator(@PathVariable("curatorCode") String curatorCode);

    @GetMapping("/user/find-curator-by-id/{curatorId}")
    Curator findGroupsCuratorById(@PathVariable("curatorId") Integer curatorId);

    @GetMapping("/process-status/get-status/{statusCode}")
    ProcessStatus getProcessStatus(@PathVariable("statusCode") String statusCode);

    @GetMapping("/process-status/get-statuses")
    List<ProcessStatus> allProcessStatuses();

    @GetMapping("/user/find-group-by-id/{groupCode}")
    Group findGroupByGroupCode(@PathVariable("groupCode") String groupCode);

    @GetMapping("/authority/find-by-account-name/{accountName}")
    User findAuthorityByAccountName(@PathVariable("accountName") String accountName);

    @GetMapping("/user/find-executors-by-id")
    List<Executor> getExecutorsById(@RequestParam(value = "executorsId") List<String> executorsId);

    @GetMapping("/process-status/get-status-codes/by-name")
    List<String> getProcessStatusCodes(@RequestParam("statusName") String statusName);

    @GetMapping("/commons/okeds/by-parent")
    List<Oked> findOkedsByParent(@RequestParam("parentCode") String parentCode);

    @GetMapping("/org-unit/all/custom-orgunits/{orgUnitId}")
    List<OrgUnitCustom> getCustomOrgUnits(@PathVariable("orgUnitId") long orgUnitId);

    @GetMapping("/user/get-users-by-orgunit-id/{orgunitid}")
    List<User> getUsersByOrgUnitId(@PathVariable("orgunitid") int orgUnitId);

    @GetMapping("/commons/groups")
    List<Group> allGroups();

    @GetMapping("/commons/privileges")
    List<Privilege> allPrivileges();

    @PostMapping("/commons/privileges-by-role-code")
    List<Privilege> getPrivilegesByRoleCode(@RequestParam("roleCode") String roleCode);

    @GetMapping("/roles/all")
    List<Role> allRoles();

    @GetMapping("/roles/roles-with-privileges")
    List<RoleGrant> allRolesWithPrivileges();

    @GetMapping("/roles/code/{roleCode}")
    Role findRoleByCode(@PathVariable("roleCode") String roleCode);

    @PostMapping("/roles/save")
    void saveRole(@RequestBody Role role);

    @RequestMapping("/audit/abis/list")
    Report<Audit> getAuditList(@RequestBody ColvirAuditFilterRequest request) throws ParseException;

    @RequestMapping("/audit/abis/comments/save")
    void saveComments(@RequestBody CommentRequest commentRequest);

    @GetMapping("/tech-usr/user/list")
    List<TechUser> getTechUserList();

    @GetMapping("tech-usr/user/find/by-account-name/{accountName}")
    TechUser findTechUserByAccount(@PathVariable("accountName") String accountName);

    @GetMapping("/tech-usr/system/list")
    List<Systems> getSystems();

    @PostMapping("/tech-usr/user/save")
    void saveTechUser(@RequestBody TechUser techUser);

    @GetMapping("/user/find-by-fio/{fio}")
    List<User> findUsersByFio(@PathVariable("fio") String fio);

    @GetMapping("/user/substitutes/{userId}")
    List<User> findUsersSubstitutes(@PathVariable("userId") int userId);

    @GetMapping("/user/find-group-codes-by-user-account/{accountName}")
    List<String> getUserGroupsByAccountName(@PathVariable("accountName") String accountName);

    @GetMapping("/commons/groups/search/by-name/{groupName}")
    List<Group> findGroupsByName(@PathVariable("groupName") String groupName);

    @RequestMapping("/org-unit/find-by-parent-code-and-name/")
    List<OrgUnit> findOrgUnitByParentCodeAndName(@RequestParam("parentCode") String parentCode, @RequestParam("orgUnitName") String orgUnitName);

    @GetMapping("/commons/group/by-org-unit-id/{orgUnitId}")
    Group findGroupByOrgUnit(@PathVariable("orgUnitId") Integer orgUnitId);

    @GetMapping("/user/find-role-codes-by-user-id/{userId}")
    List<String> findUserRolesByUserId(@PathVariable("userId") Integer userId);

    @RequestMapping("/colvir-view-group/list")
    List<GroupColvirView> getGroupsByColvirViews(@RequestParam("viewIdList") List<Integer> viewIdList);


    @RequestMapping("/colvir-view-group/add")
    void addViewGroups(@RequestBody ManageGroupForm manageGroupForm);

    @RequestMapping("/colvir-view-group/remove")
    void deleteViewGroups(@RequestParam("viewId") Integer viewId, @RequestParam("groupId") Integer groupId);

    @RequestMapping("/colvir-view-group/find/by-group-name")
    List<Integer> findviewListByGroupName(@RequestParam("groupName") String groupName);

    @RequestMapping("/colvir-view-group/colvir/reports")
    Report<ColvirReport> getColvirReports(@RequestBody ColvirReportOwnerRequest request);

    @RequestMapping("/colvir-view-group/colvir/reports/all")
    List<ColvirReport> getColvirReportList();

    @RequestMapping("/colvir-view-group/find/group-names/by-view-id")
    List<String> getGroupNamesByViewId(@RequestParam("viewId") Integer viewId);

    @RequestMapping("/colvir-view-group/list/all")
    List<GroupColvirView> findColvirViewsGroupsAll();

    @GetMapping("/commons/customer-document-type/{id}")
    CustomerDocumentType getCustomerDocumentTypeById(@PathVariable("id") String id);

    @GetMapping("/commons/customer-document-type-by-code/{code}")
    CustomerDocumentType getCustomerDocumentTypeByCode(@PathVariable("code") String code);

    @GetMapping("/org-unit/get-children-by-id/{orgUnitId}")
    List<OrgUnit> getChildren(@PathVariable("orgUnitId") int orgUnitId);

    @GetMapping("/org-unit/get-all-parents-by-id/{childOrgUnitId}")
    List<OrgUnit> getAllParents(@PathVariable("childOrgUnitId") int childOrgUnitId);

    @GetMapping("/commons/enterpreneur/{iinBin}")
    Enterpreneur findEnterpreneur(@PathVariable("iinBin") String iinBin);

    @GetMapping("/kato/root")
    List<Kato> getKatoRootNew();

    @GetMapping("/kato/level2")
    List<Kato> get2LevelKatoNew();

    @GetMapping("/kato/list-by-parent/id/{parentId}")
    List<Kato> getKatoByParentIdNew(@PathVariable("parentId") Integer parentId);

    @GetMapping("/kato/list-by-parent/code/{parentCode}")
    List<Kato> getKatoByParentCodeNew(@PathVariable("parentCode") String parentCode);

    @GetMapping("/kato/by-code/{code}")
    Kato getKatoByCodeNew(@PathVariable("code") String code);

    @GetMapping("/kato/hierarchy-from-leaf/{katoCode}")
    List<Kato> getKatoHierarchyFromLeafNew(@PathVariable("katoCode") String katoCode);

    @GetMapping("/phone-number-guid/all")
    List<PhoneNumberGuid> getAllPhoneNumberGuid();

    @PostMapping("/phone-number-guid/update")
    PhoneNumberGuid updatePhoneNumberGuid(@RequestBody PhoneNumberGuid phoneNumberGuid);

    @GetMapping("/phone-number-guid/get/{id}")
    PhoneNumberGuid getPhoneNumberGuidById(@PathVariable("id") Integer id);*/

}